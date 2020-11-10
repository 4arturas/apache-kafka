package com.example;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Properties;

@Slf4j
public class KafkaProducerV1
{
    public static void main(String[] args)
    {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093,localhost:9094");
        props.put("acks", "1");
        props.put("retries", "10");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://127.0.0.1:8081");

        KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<String, Customer>(props);
        String topic = "customer-avro";
        Customer customer = Customer.newBuilder()
                .setFirstName("Gogo")
                .setLastName("Gugu")
                .setAge(56)
                .setHeight(190)
                .setWeight(100)
                .setAutomatedEmail(false)
                .build();

        ProducerRecord<String, Customer> producerRecord = new ProducerRecord<String, Customer>( topic, customer );

        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if ( e == null )
                {
                    log.info("Success! " + recordMetadata.toString());
                }
                else
                {
                    log.error(e.getMessage());
                }
            }
        });
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}

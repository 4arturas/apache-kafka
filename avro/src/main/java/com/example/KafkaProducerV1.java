package com.example;

import com.example.kafka.model.Test;
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
//        props.put("bootstrap.servers", "localhost:9093,localhost:9094");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "1");
        props.put("retries", "10");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://127.0.0.1:8080");

        KafkaProducer<String, Test> kafkaProducer = new KafkaProducer<String, Test>(props);
        String topic = "test-avro";


        Test test = Test.newBuilder().setId("1").build();


        ProducerRecord<String, Test> producerRecord = new ProducerRecord<String, Test>( topic, test );

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



import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import models.Event;

import java.util.Properties;

import static java.lang.Thread.sleep;


@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {

        EventGenerator eventGenerator = new EventGenerator();

        Properties props = new Properties();
        props.put("bootstrap.servers", "INTERNAL://192.168.56.10:9092");
//        props.put("bootstrap.servers", "192.168.56.10:29092");
//        props.put("bootstrap.servers", "PLAINTEXT://192.168.56.10:9092");
//        props.put("advertised.listeners", "PLAINTEXT://192.168.56.10:9092");
//        props.put("bootstrap.servers", "localhost:9093,localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // producer acks
        props.setProperty("acks", "1");
        props.setProperty("retries", "3");
        props.setProperty("linger.ms", "1");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for(int i = 1; i <= 10; i++) {
            log.info("Generating event #" + i);

            Event event = eventGenerator.generateEvent();

            String key = extractKey(event);
            String value = extractValue(event);

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("user-tracking", key, value);

            log.info("Producing to Kafka the record: " + key + ":" + value);
            producer.send(producerRecord);

            sleep(1000);
        }

        producer.close();
    }

    private static String extractKey(Event event) {
        return event.getUser().getUserId().toString();
    }

    private static String extractValue(Event event) {
        return String.format("%s,%s,%s", event.getProduct().getType(), event.getProduct().getColor(), event.getProduct().getDesignType());
    }


}


import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

import java.util.Properties;

public class Props
{
    public static Properties properties_ForConsumer()
    {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
        props.put("bootstrap.servers", "192.168.56.10:9092");
//        props.put("bootstrap.servers", "localhost:9093,localhost:9094");
        props.put("group.id", "user-tracking-consumer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static Properties properties_ForProducer() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
        props.put("bootstrap.servers", "192.168.56.10:9092");
//        props.put("bootstrap.servers", "localhost:9093,localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // producer acks
        props.setProperty("acks", "1");
        props.setProperty("retries", "3");
        props.setProperty("linger.ms", "1");
        return props;
    }

    public static Properties properties_ForFroudDetectionApplication()
    {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "fraud-detection-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093, localhost:9094");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        return props;
    }
}

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Properties;

import static java.util.Arrays.asList;

@Slf4j
public class ConsumerMain
{

    public static void main(String[] args) {

        SuggestionEngine suggestionEngine = new SuggestionEngine();

        Properties props = Props.properties_ForConsumer("192.168.56.13:29092", "example-topic" );

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        final String topic = "example-topic";
//        final String topic = "user-tracking";

        consumer.subscribe(asList( topic ));

        while (true)
        {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                suggestionEngine.processSuggestions(record.key(), record.value());
            }
        }
    }
}
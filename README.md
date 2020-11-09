# apache-kafka
02
D:\kafka_2.13-2.6.0\bin\windows\zookeeper-server-start.bat D:\kafka_2.13-2.6.0\config\zookeeper.properties

xcopy D:\kafka_2.13-2.6.0\config\server.properties D:\kafka_2.13-2.6.0\config\server-1.properties
xcopy D:\kafka_2.13-2.6.0\config\server.properties D:\kafka_2.13-2.6.0\config\server-2.properties

D:\kafka_2.13-2.6.0\bin\windows\kafka-server-start.bat D:\kafka_2.13-2.6.0\config\server-1.properties
D:\kafka_2.13-2.6.0\bin\windows\kafka-server-start.bat D:\kafka_2.13-2.6.0\config\server-2.properties

03
D:\kafka_2.13-2.6.0\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9093 --partitions 2 --replication-factor 2 --topic user-tracking
 
D:\kafka_2.13-2.6.0\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9093 user-tracking

04 Avro 
https://apache.mirror.serveriai.lt/avro/avro-1.10.0/java/avro-tools-1.10.0.jar
set JAVA_HOME=%ProgramFiles%\Java\jdk1.7.0_79
cd D:\JAVA_PROJECTS\apache-kafka\src\main\java\schemas
java -jar D:\avro-tools-1.10.0.jar compile schema user_schema.avsc .
"%ProgramFiles%\Java\jdk1.8.0_25\bin\java" -jar D:\avro-tools-1.10.0.jar compile schema user_schema.avsc .
    


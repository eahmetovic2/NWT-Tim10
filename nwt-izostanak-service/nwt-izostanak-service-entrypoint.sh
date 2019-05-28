while ! nc -z eureka-service 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done

java -jar /opt/izostanak/nwt-ocjena-service-0.0.1-SNAPSHOT.jar
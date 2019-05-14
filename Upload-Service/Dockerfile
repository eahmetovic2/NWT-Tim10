FROM java:8
MAINTAINER javaonfly
COPY /target/uploadservice-0.0.1-SNAPSHOT.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/uploadservice-0.0.1-SNAPSHOT.jar"]
EXPOSE 8000

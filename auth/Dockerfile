FROM java:8
MAINTAINER javaonfly
COPY /target/auth-0.0.1-SNAPSHOT.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/auth-0.0.1-SNAPSHOT.jar"]
EXPOSE 9100

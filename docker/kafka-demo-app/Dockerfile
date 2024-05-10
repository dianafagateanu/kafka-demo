FROM openjdk:21-slim

ENV HOSTNAME "localhost"

RUN apt update

# Clean APK cache
RUN rm -rf /var/cache/apk/*

RUN mkdir -p -v /var/kafka-demo-app/conf/
RUN mkdir -p -v /var/kafka-demo-app/lib/
RUN mkdir -p -v /var/kafka-demo-app/log/

COPY /docker-entrypoint.sh /

RUN chmod +x /docker-entrypoint.sh

EXPOSE 8084
EXPOSE 5005
EXPOSE 10080

ENTRYPOINT ["/docker-entrypoint.sh"]

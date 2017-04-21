FROM java:8
MAINTAINER przodownikR1
EXPOSE 8761
VOLUME /tmp
WORKDIR /eureka-point
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD eureka-point.jar discoveryService.jar
RUN sh -c 'touch /discoveryService.jar'
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar discoveryService.jar


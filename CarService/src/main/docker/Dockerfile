FROM java:8
MAINTAINER przodownikR1
VOLUME /tmp
EXPOSE 9010
WORKDIR /car-service

ADD car-service.jar carService.jar
RUN sh -c 'touch /carService.jar'
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar carService.jar

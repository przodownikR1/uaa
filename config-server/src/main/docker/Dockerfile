FROM java:8
MAINTAINER przodownikR1
EXPOSE 8888
VOLUME /tmp
ADD config-server-0.0.2.jar config-server.jar
RUN sh -c 'touch /config-server.jar'
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar config-server.jar


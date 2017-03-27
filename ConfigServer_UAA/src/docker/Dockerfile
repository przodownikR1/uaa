FROM java:8
MAINTAINER przodownikR1
EXPOSE 8888
VOLUME /tmp
WORKDIR /config-server

ADD config-server.jar configServer.jar
RUN sh -c 'touch /configServer.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/configServer.jar" ]

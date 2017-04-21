FROM java:8
MAINTAINER przodownikR1
VOLUME /tmp
EXPOSE 8888
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD config-server.jar config-server.jar
RUN sh -c 'touch /config-server.jar'
ENV JAVA_OPTS="-Xmx128m -Xmx256m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /config-server.jar" ]


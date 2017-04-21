FROM java:8
MAINTAINER przodownikR1
EXPOSE 9040
VOLUME /tmp
WORKDIR /nbp-service
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD nbp-service.jar nbp-service.jar
RUN sh -c 'touch /nbp-service.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/nbp-service.jar" ]


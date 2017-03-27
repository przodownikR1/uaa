FROM java:8
MAINTAINER przodownikR1
EXPOSE 9040
VOLUME /tmp
WORKDIR /nbp-service

ADD nbp-service.jar nbpService.jar
RUN sh -c 'touch /nbpService.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/nbpService.jar" ]

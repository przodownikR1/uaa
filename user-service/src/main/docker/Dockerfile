FROM java:8
MAINTAINER przodownikR1
EXPOSE 9020
VOLUME /tmp
WORKDIR /user-service

ADD user-service.jar userService.jar
RUN sh -c 'touch /userService.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/userService.jar" ]


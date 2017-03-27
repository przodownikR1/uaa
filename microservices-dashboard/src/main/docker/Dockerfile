FROM java:8
MAINTAINER przodownikR1
VOLUME /tmp
WORKDIR /microservices-dashboard

ADD microservices-dashboard.jar microservicesDashboard.jar
RUN sh -c 'touch /microservicesDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/microservicesDashboard.jar" ]

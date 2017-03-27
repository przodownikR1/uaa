FROM java:8
MAINTAINER przodownikR1
EXPOSE 8040
VOLUME /tmp
WORKDIR /admin-service

ADD admin-dashboard.jar adminDashboard.jar
RUN sh -c 'touch /adminDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/adminDashboard.jar" ]

FROM java:8
MAINTAINER przodownikR1
VOLUME /tmp
WORKDIR /microservices-dashboard
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD microservices-dashboard.jar microservicesDashboard.jar
RUN sh -c 'touch /microservicesDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/microservicesDashboard.jar" ]

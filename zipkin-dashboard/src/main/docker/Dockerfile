FROM java:8
MAINTAINER przodownikR1
EXPOSE 8060
VOLUME /tmp
WORKDIR /zipkin-dashboard

ADD zipkin-dashboard.jar zipkinDashboard.jar
RUN sh -c 'touch /zipkinDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/zipkinDashboard.jar" ]

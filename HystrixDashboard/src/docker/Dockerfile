FROM java:8
MAINTAINER przodownikR1
EXPOSE 8050
VOLUME /tmp
WORKDIR /hystrix-dashboard

ADD hystrix-dashboard.jar hystrixDashboard.jar
RUN sh -c 'touch /hystrixDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/hystrixDashboard.jar" ]

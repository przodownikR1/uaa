FROM java:8
MAINTAINER przodownikR1
EXPOSE 8050
VOLUME /tmp
WORKDIR /hystrix-dashboard
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD hystrix-dashboard.jar hystrixDashboard.jar
RUN sh -c 'touch /hystrixDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/hystrixDashboard.jar" ]

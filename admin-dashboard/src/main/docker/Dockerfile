FROM java:8
MAINTAINER przodownikR1
EXPOSE 8040
VOLUME /tmp
WORKDIR /admin-service
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD admin-dashboard.jar adminDashboard.jar
RUN sh -c 'touch /adminDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar adminDashboard.jar 


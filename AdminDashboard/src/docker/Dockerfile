FROM java:8
MAINTAINER przodownikR1
VOLUME /tmp
WORKDIR /admin-dashboard

ADD admin_dashboard-0.0.1-SNAPSHOT.jar admin_dashboard.jar
RUN sh -c 'touch /admin_dashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/admin_dashboard.jar" ]
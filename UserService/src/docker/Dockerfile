FROM java:8

MAINTAINER przodownikR1

VOLUME /tmp

WORKDIR /admin-dashboard

ADD adminDashboard-0.0.1-SNAPSHOT.jar adminDashboard.jar
RUN sh -c 'touch /adminDashboard.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /adminDashboard.jar" ]
FROM java:8
VOLUME /tmp
ADD nbp-cloud-0.0.1-SNAPSHOT.jar nbp-cloud.jar 
RUN sh -c 'touch /nbp-cloud.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/nbp-cloud.jar" ]
EXPOSE 9008

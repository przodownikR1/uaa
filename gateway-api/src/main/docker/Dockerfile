FROM java:8
MAINTAINER przodownikR1
EXPOSE 9001
VOLUME /tmp
WORKDIR /gateway-api
RUN rm -rf /log
RUN mkdir /log && chmod 0755 /log
ADD gateway-api.jar gatewayApi.jar
RUN sh -c 'touch /gatewayApi.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar","/gatewayApi.jar" ]

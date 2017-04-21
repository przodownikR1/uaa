
FROM logstash:latest

RUN logstash-plugin install logstash-input-file
RUN logstash-plugin install logstash-filter-date
RUN logstash-plugin install logstash-filter-json
RUN logstash-plugin install logstash-filter-grok
RUN logstash-plugin install logstash-input-jmx

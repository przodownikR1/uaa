# uaa
spring cloud uaa 


http://localhost:9001/uaa/oauth/authorize?response_type=code&client_id=foo&redirect_uri=http://localhost:9001/uaa/response

curl -XPOST -k foo:bar@localhost:9001/uaa/oauth/token  -d grant_type=authorization_code  -d code={} -d redirect_uri=http://localhost:9001/uaa/response  

curl -H "Authorization: Bearer {}" http://localhost:9001/uaa/car/1

 nc localhost 5000 < /path/to/logfile.log


I. Codebase
Docker : (Jedno repozytorium ze śledzeniem zmian w systemie kontroli wersji.)
One codebase tracked in revision control, many deploys (gradle-git-properties)
●
II. Dependencies
Docker: (Jawne deklarowanie i izolowanie zależności.)
Explicitly declare and isolate dependencies
●
III. Config (spring-cloud-config , bus)
Docker: Zapisywanie konfiguracji w zmiennych środowiskowych, a nie w plikach zapisanych w repozytorium.
Store config in the environment
●
IV. Backing services (spring-security-oauth2 , hystrix)
Docker : Traktuj usługi pomocnicze jak zewnętrzne zasoby.
Treat backing services as attached resources
●
V. Build, release, run (spring-boot-maven-plugin) One build → many deploys
Docker : Wyraźnie oddziel etap budowania od etapu uruchamiania.
Strictly separate build and run stages
●
VI. Processes (jar not war) (stateless, share nothing, session caching..spring-session, redis , etc)
Docker : Uruchamiaj aplikację jako jeden lub kilka bezstanowych procesów.
Execute the app as one or more stateless processes
●
VII. Port binding (bootstrap,server.port, docker → env )
Docker : Udostępniaj serwisy na oddzielnych portach.
Export services via port binding
●
VIII. Concurrency (scalability, eventually consistency, stateless, horizontal scale, etc)
Docker : Przygotuj procesy skalowania.
Scale out via the process model
●
IX. Disposability (docker, docker-compose,vagrant, ansible, CI ) Amazon, Allego example
Docker :
Maksymalizuj stabilność poprzez szybkie uruchamianie i bezproblemowe wyłączanie.
Maximize robustness with fast startup and graceful shutdown
●
X. Dev/prod parity (@Profile , config , minimalize gap between dev and prod)
Docker : Utrzymuj możliwie zbliżone środowiska programistyczne, demonstracyjne i produkcyjne
Keep development, staging, and production as similar as possible
●
XI. Logs (ELK, logstash, logback, kafka)
Docker : Traktuj logi jak strumienie zdarzeń.
Treat logs as event streams
●
XII. Admin processes (liquibase, flyway)
Docker : Uruchamiaj zadania do administracji i zarządzania jako jednorazowe procesy.
Run admin/management tasks as one-off processes

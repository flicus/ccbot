FROM openjdk:11

RUN mkdir "/usr/app" && mkdir "/usr/app/config" && mkdir "/usr/app/logs"

COPY target/cyprus-car-bot-1.0-SNAPSHOT.jar /usr/app
COPY bin/carbot.sh /usr/app
COPY src/main/resources/application.local.yaml /usr/app/config

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "cyprus-car-bot-1.0-SNAPSHOT.jar", "--spring.config.location=/usr/app/config/*/"]
FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/auth-service
ADD @project.build.finalName@.war /usr/local/auth-service/

EXPOSE 8088

ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
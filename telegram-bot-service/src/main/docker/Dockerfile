FROM openjdk:8-jre-alpine
ENV APP_FILE @project.build.finalName@.jar
ENV APP_HOME /usr/apps
EXPOSE 8080
COPY $APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
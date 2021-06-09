FROM openjdk:11-jdk-oracle
ENV APP_FILE @project.build.finalName@.war
ENV APP_HOME /usr/apps

ENV GOOGLE_APPLICATION_CREDENTIALS service-account.json

EXPOSE 8080
COPY $APP_FILE $APP_HOME/
WORKDIR $APP_HOME

COPY service-account.json $APP_HOME

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
#CMD ["exec docker login; docker push tuanalexeu/logiweb-service:final-task"]
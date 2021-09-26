FROM openjdk:11
EXPOSE 9805
ARG JAR_FILE=target/project-data.jar
ADD ${JAR_FILE} project-data.jar
ENTRYPOINT ["java","-jar","/project-data.jar"]

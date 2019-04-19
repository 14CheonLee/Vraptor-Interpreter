# Dockerfile for Vraptor-Interpreter
FROM arm32v7/tomcat:9-jre8

MAINTAINER Byeonggil-Jung "jbkcose@gmail.com"

RUN apt-get upgrade -y && apt-get update -y
RUN apt-get install -y openjdk-8-jdk

# Change the original ROOT directory to ROOT_old in tomcat/webapps
RUN mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/ROOT_old

# Download and build the gradle wrapper
COPY ["./build.gradle", "./gradlew", "./gradlew.bat", "./settings.gradle", "/Vraptor-Interpreter/"]
COPY ["./gradle/", "/Vraptor-Interpreter/gradle/"]
RUN /Vraptor-Interpreter/gradlew wrapper

# Copy all source codes to container
COPY [".", "/Vraptor-Interpreter"]

WORKDIR /Vraptor-Interpreter

# Build the gradle
RUN ./gradlew build
RUN ./gradlew war

# Copy the war file to tomcat/webapps with changing the name to 'ROOT.war'
RUN cp ./build/libs/vraptor-interpreter.war /usr/local/tomcat/webapps/ROOT.war

# Run the application
WORKDIR /usr/local/tomcat/bin

ENTRYPOINT ["catalina.sh"]
CMD ["run"]
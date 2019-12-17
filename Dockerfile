FROM openjdk:11-jre-alpine
MAINTAINER lazyzzz scott.li.xia@gmail.com

VOLUME /tmp

ARG PORT=8080
ARG TIME_ZONE=Asia/Shanghai
ARG JAR_FILE=build/libs/myblog-0.0.1-SNAPSHOT.jar

ENV TZ=${TIME_ZONE}
ENV JAVA_OPTS="-Xms256m -Xmx256m"

COPY ${JAR_FILE} myblog.jar

EXPOSE ${PORT}

ENTRYPOINT java ${JAVA_OPTS} -server -jar myblog.jar
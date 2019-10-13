FROM javatechy/openjdk-8-slim

ARG ARTIFACT_NAME
ENV ARTIFACT_NAME=${ARTIFACT_NAME}
ENV BOOTAPP_JAVA_OPTS -Xms256m -Xmx256m
ENV SERVER_PORT 8080

ADD ${ARTIFACT_NAME} .
ADD entrypoint.sh .
RUN chmod 555 /entrypoint.sh

ENTRYPOINT [ "bash", "-c", "./entrypoint.sh" ]

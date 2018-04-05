FROM 634957622823.dkr.ecr.ap-south-1.amazonaws.com/tomcat8:latest

USER root

COPY target/users-0.0.1-SNAPSHOT.war   /usr/share/tomcat8/webapps/user.war

COPY docker/catalina_base/bin/setenv.sh /usr/share/tomcat8/bin/
RUN /usr/share/tomcat8/bin/setenv.sh

RUN mkdir /staging
COPY docker/config-bootstrap.sh /staging
RUN chmod 755 /staging && \
    chmod 755 /staging/config-bootstrap.sh

CMD [ "/usr/local/bin/dumb-init", "/staging/config-bootstrap.sh" ]

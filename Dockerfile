FROM davidcaste/alpine-tomcat:tomcat8

COPY hackreka.war $TOMCAT_HOME/webapps/.

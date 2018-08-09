#基础镜像
FROM hub.c.163.com/library/tomcat

#作者
MAINTAINER liqitian

#定义工作目录
ENV WORK_PATH /usr/local/tomcat/conf

#定义要替换的server.xml文件名
ENV SERVER_CONF_FILE_NAME server.xml

ADD /root/jjxy/family-school/target/*.war /usr/local/tomcat/webapps/family-school.war

#删除原文件server.xml
RUN rm $WORK_PATH/$SERVER_CONF_FILE_NAME

#复制文件server.xml
COPY  ./$SERVER_CONF_FILE_NAME $WORK_PATH/
EXPOSE 8080

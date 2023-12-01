

FROM openjdk:8-jdk-alpine
# 设置工作目录在镜像的 /app 目录下
WORKDIR /app
# 将jar包添加到容器中并更名为app.jar
COPY  target/npm-dependency-0.0.1-SNAPSHOT.jar /app/
# 运行jar包
ENTRYPOINT ["java","-jar","npm-dependency-0.0.1-SNAPSHOT.jar"]
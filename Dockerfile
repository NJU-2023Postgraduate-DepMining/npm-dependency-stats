FROM maven:3.8.6-openjdk-8
# 设置工作目录在镜像的 /app 目录下
WORKDIR /app
COPY . .
RUN mvn -DskipTests=true clean package
EXPOSE 8080
# 运行jar包
ENTRYPOINT ["java","-jar","./target/npm-dependency-0.0.1-SNAPSHOT.jar"]
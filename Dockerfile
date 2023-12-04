# split build as a single stage
FROM maven:3.8.6-openjdk-8 as builder
WORKDIR /build
COPY . .
RUN mvn -DskipTests=true clean package

# put jar package under /app
FROM maven:3.8.6-openjdk-8
WORKDIR /app
COPY --from=builder /build/target/npm-dependency-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/npm-dependency-0.0.1-SNAPSHOT.jar"]

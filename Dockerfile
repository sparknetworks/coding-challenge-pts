FROM registry.affinitas.io/tools/openjdk-11:latest as builder
COPY . /
RUN ./gradlew build --no-daemon

FROM registry.affinitas.io/tools/openjdk-11:latest as runner
COPY --from=builder build/libs/coding-challenge-pts-0.0.1-SNAPSHOT.jar /deployment/app.jar
WORKDIR /deployment
EXPOSE 8080git
ENTRYPOINT ["java", "-jar", "app.jar"]
FROM amd64/amazoncorretto:17

WORKDIR /app

COPY ./build/libs/toaster-0.0.1-SNAPSHOT.jar /app/toaster.jar
ENV	USE_PROFILE dev

CMD ["java", "-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=${USE_PROFILE}", "-jar", "toaster.jar"]

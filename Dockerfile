FROM amazoncorretto:21

WORKDIR /app
COPY  target/restaurant-*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Dspring.jwt.secret=${JWT_SECRET}", "-Dspring.datasource.url=${DB_URL}", "app.jar"]


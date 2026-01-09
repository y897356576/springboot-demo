FROM dragonwell-registry.cn-hangzhou.cr.aliyuncs.com/dragonwell/dragonwell:21-alpine
COPY target/springboot-demo-*.jar /springboot-demo-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springboot-demo-app.jar"]
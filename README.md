# Java/Spring with MySQL template for Crafting Sandbox

This is a Java/[Spring](https://spring.io/) with MySQL template, configured for quick development setup in [Crafting Sandbox](https://crafting.readme.io/docs).

## Specifications

This template defines a single `/ping` route:
```java
@GetMapping("/ping")
public String ping(@RequestParam(defaultValue = "...") String ping) {
    JSONObject pong = new JSONObject();

    pong.put("ping", ping);
    pong.put("received_at", Instant.now());

    return pong.toString();
}
```

This route receives a query string, and responds with the param string and the current time. For example:
```bash
$ curl --request GET 'localhost:3000/ping?ping=hello'
{"ping":"hello","received_at":"XXXX-XX-XXXXX:XX:XX.XXXXXXX"}
```

Template is configured with MySQL, with driver dependency defined in [`build.gradle`](ping/build.gradle), and connection configuration in [`application.properties`](ping/src/main/resources/application.properties):
```java
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_SERVICE_HOST:mysql}:${MYSQL_SERVICE_PORT:3306}/superhero
spring.datasource.username=brucewayne
spring.datasource.password=batman
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
#spring.jpa.show-sql: true
server.port=3000
```

Server port is set to `3000`, which is the same as the port defined in App configuration below.

To run the app:
```bash
cd ping && ./gradlew bootRun
```

## App configuration

The following [App configuration](https://crafting.readme.io/docs/app-spec) was used to create this template:

```yaml
endpoints:
- http:
  routes:
  - backend:
      port: http
      target: java-spring
    path_prefix: /
name: app
services:
- description: A Java/Spring template
name: java-spring
workspace:
  checkouts:
  - path: src/template-java-spring
    repo:
      git: https://github.com/crafting-dev/template-java-spring.git
  packages:
  - name: openjdk
    version: ~14
  ports:
  - name: http
    port: 3000
    protocol: HTTP/TCP
- managed_service:
  properties:
    database: superhero
    password: batman
    username: brucewayne
  service_type: mysql
  version: "8"
name: mysql
```

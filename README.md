# Java/Spring with MySQL template for Crafting Sandbox

This is a Java/[Spring](https://spring.io/) with MySQL template, configured for quick development setup in [Crafting Sandbox](https://docs.sandboxes.cloud/docs).

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

## App Definition

The following [App Definition](https://docs.sandboxes.cloud/docs/app-definition) was used to create this template:

```yaml
endpoints:
- name: api
  http:
    routes:
    - pathPrefix: "/"
      backend:
        target: java-spring
        port: api
    authProxy:
      disabled: true
workspaces:
- name: java-spring
  description: Template backend using Java/Spring
  ports:
  - name: api
    port: 3000
    protocol: HTTP/TCP
  checkouts:
  - path: backend
    repo:
      git: https://github.com/crafting-dev/template-java-spring
  packages:
  - name: openjdk
    version: 14.0.2
dependencies:
- name: mysql
  serviceType: mysql
  version: '8'
  properties:
    database: superhero
    password: batman
    username: brucewayne
```

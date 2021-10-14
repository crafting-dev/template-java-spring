# Java Spring template for Cloud Sandbox

This is a Java [Spring](https://spring.io/) template, configured for quick development setup in Cloud Sandbox.

## Specifications

The following `App configuration` was used to create this template:

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
    - path: template-java-spring
      repo:
        git: https://github.com/crafting-dev/template-java-spring.git
    packages:
    - name: openjdk
      version: ~14
    - name: maven
      version: ~3
    ports:
    - name: http
      port: 3000
      protocol: HTTP/TCP
```

To run the app:
```bash
cd spring-boot && ./mvnw spring-boot:run
```
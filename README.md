# TODO TASK TRACKER <sub>(JR project)</sub>

The project is a simple task tracker, implemented lazy loading of the task list.
<br>Basic functionality:
- add a task;
- change the task;
- delete a task
- sorting tasks by status, description, id;
- filtering tasks by status;
---

> Author: *Komarov Denis*

---

### Technologies used:

- Java 17
- Spring Boot 3.1.2
- Spring Data
- Vaadin
- MySQL
- Lombok

---
### The structure of the table in the database:

| ColumnName  | Datatype     | PK       | NN       | AI       |
|-------------|--------------|----------|----------|----------|    
| id          | INT          | &#10004; | &#10004; | &#10004; |
| description | VARCHAR(100) |          | &#10004; |          |
| status      | INT          |          | &#10004; |          | 
---

In the root of the project there is a script for test filling in the table



> Basic configuration in application.yaml
```
spring:
datasource:
url: jdbc:mysql://localhost:3307/todo
username: root
password: root

jpa:
hibernate:
ddl-auto: none
show-sql: true
server:
port: 8081
```
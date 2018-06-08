api-portfolio is bootstrapped using https://start.spring.io with Web, JPA and Apache Derby dependencies

### Install the dependencies

```
mvn clean install
```

## API Portfolio Development

To start a development server for working on the api-portfolio run:

```
mvn spring-boot:run
```


## Low Level Design 

### Supporting API

This application is designed in such a way that advisors are pre-loaded independently and then models are created and associated with the  advisors. So , during application startup, two default advisors are created. More advisors can be added during runtime with admin access. To identify list of advisors , use following API Get call with admin login.

```
curl -u admin:admin http://localhost:8080/admin/api/v1/advisors
```

### For core APIs
Advisor IDs can be copied from above admin API response and other core APIs can be triggered as follows

```
curl -u advisor1:advisor1 -X PUT @model.req.json http://localhost:8080/admin/api/v1/advisors
```

 







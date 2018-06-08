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

This application is designed in such a way that advisors are pre-loaded independently and then models are created and associated with the  advisors. So , during application startup, two default advisors are created. More advisors can be added during runtime with admin API. To identify list of advisors and their advisorID to be used in other APIs, use following API Get call response.

```
curl http://localhost:8080/admin/api/v1/advisors
```

### For core APIs
Advisor IDs can be copied from above admin API response and other core APIs can be triggered as follows

```
curl -X PUT @model.req.json http://localhost:8080/api/v1/advisor/{advisorId}/model
```

 







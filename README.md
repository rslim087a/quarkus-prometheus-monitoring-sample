# Quarkus Prometheus Monitoring Sample

This project is a sample Quarkus application with Prometheus monitoring integration.

## Prerequisites

- JDK 11+
- Maven 3.8.1+

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it's not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## API Endpoints

The following endpoints are available:

- `GET /`: Root endpoint
- `POST /items`: Create a new item
- `GET /items/{item_id}`: Retrieve an item
- `PUT /items/{item_id}`: Update an item
- `DELETE /items/{item_id}`: Delete an item
- `GET /q/metrics`: Prometheus metrics endpoint

## Monitoring

The application exposes Prometheus metrics at the `/q/metrics` endpoint. You can configure your Prometheus server to scrape these metrics for monitoring.

## Testing with Postman

A Postman collection is provided for testing the API endpoints. Import the `Quarkus Metrics App.postman_collection.json` file into Postman to use it.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A JAX-RS implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Micrometer Registry Prometheus ([guide](https://quarkus.io/guides/micrometer)): Enable Prometheus support for Micrometer

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
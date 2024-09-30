# Quarkus Prometheus Monitoring Sample

This project is a sample Quarkus application with Prometheus monitoring integration.

## Prerequisites

* Java 11 or higher
* Maven
* Postman (optional, for testing API endpoints)

## Setup

1. Clone the repository:

```bash
git clone https://github.com/rslim087a/quarkus-prometheus-monitoring-sample.git
cd quarkus-prometheus-monitoring-sample
```

2. Build the project:

```bash
./mvnw clean install
```

## Running the Application

To run the application in development mode, use the following command:

```bash
./mvnw quarkus:dev
```

The application will start and be available at `http://localhost:8080`.

## API Endpoints

The following endpoints are available:

* `GET /`: Root endpoint
* `POST /items`: Create a new item
* `GET /items/{item_id}`: Retrieve an item
* `PUT /items/{item_id}`: Update an item
* `DELETE /items/{item_id}`: Delete an item
* `GET /q/metrics`: Prometheus metrics endpoint

## Testing with Postman

A Postman collection is provided for testing the API endpoints. To use it:

1. Open Postman
2. Click on "Import" and select the `quarkus-metrics-postman-collection.json` file
3. The collection "Quarkus Metrics App" will be added to your Postman workspace
4. You can now use the following requests to test each endpoint:
   * Root: GET request to test the root endpoint
   * Create Item: POST request to create a new item
   * Get Item: GET request to retrieve an item by ID
   * Update Item: PUT request to update an existing item
   * Delete Item: DELETE request to remove an item
   * Get Metrics: GET request to fetch Prometheus metrics

## Monitoring

The application exposes Prometheus metrics at the `/q/metrics` endpoint. You can configure your Prometheus server to scrape these metrics for monitoring.

To view the raw metrics, open a web browser and go to:

```
http://localhost:8080/q/metrics
```

## Development

If you want to make changes to the project:

1. Make your changes to the `MetricsResource.java` file or other relevant files.
2. The application will automatically reload if you're running in dev mode.
3. If not in dev mode, rebuild the project using `./mvnw clean install` and restart the application.

## Troubleshooting

If you encounter any issues:

1. Ensure you're using Java 11 or higher:

```bash
java -version
```

2. Make sure all dependencies are correctly installed:

```bash
./mvnw dependency:resolve
```

3. Check that you're in the correct directory and running the commands from the project root.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
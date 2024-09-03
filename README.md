## SpringBoot InfluxDB Demo App

### Description

This is a demo application showing how to integrate InfluxDB v3 within a SpringBoot java application along with SpringBoot Actuator and Micrometer for application health and performance monitiring and logging purpose.

The application is a simple card game and the core logic centered around three key classes: “CardController.java”, “DeckOfCardService.java”, and “InfluxDBConfig.java”. The CardController serves as the entry point, handling HTTP requests from users. When a request is made to the /card endpoint, the controller calls the DeckOfCardService, which interacts with the external Deck of Cards API to fetch a random card and return it as an SVG image via index.html webpage to the client.

Meanwhile, the InfluxDBConfig.java class configures the Micrometer integration with InfluxDB based on application.properties file. As the application processes requests, metrics such as response times and request counts are collected by Micrometer and sent to InfluxDB Bucket for storage. These metrics are then visualized in Grafana, allowing developers to monitor the application’s performance in real-time.

### How to run

- Download/Clone the project
- Open application.properties file and edit the InfluxDB properties with your InfluxDB account information namely "API Token", "Org", "Bucket" and "URI" all of which can be found in the InfluxDB portal.
- Make sure maven has sync up and downloaded all the relevant dependencies
- Build & Run the project by typing `./mvnw org.springframework.boot:spring-boot-maven-plugin:run`
- Open `localhost:8080` to interact with the app as it logs all data in the background
- Open InfluxDB UI Dashboard and query your bucket to see the data that is logged, you can also see the Springboot actuator endpoints at `http://localhost:8080/actuator/metrics`

![Architecture Diagram](https://github.com/suyashcjoshi/springboot-influxdb-app/blob/main/src/main/resources/static/PickACard.png)

### Contact

For any feedback, questions etc, please open a GitHub issue or reach out on our [community](https://community.influxdata.com)


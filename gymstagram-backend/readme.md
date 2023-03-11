This readme is to initialize the backend of gymstagram and gives a brief overivew of the APIs available. 

# Prerequisites
Before you can start the backend, you'll need to have the following tools installed:

- [Java Development Kit (JDK) 17](https://www.oracle.com/ca-en/java/technologies/downloads/#java17)
- [MongoDB (Optional)](https://www.mongodb.com/try/download/community-kubernetes-operator)

Very important Note: Ask Jason or Matt to add your Connection IP Address to our IP Access List in our MongoDB 

# Getting Started

1) Clone this repository to your local machine.
2) To run the backend using a local database, add ```spring.data.mongodb.uri=mongodb://localhost:27017/test``` to ```gymstagram-backend\src\main\resources\application.properties```
3) Start the backend by runing the following command  ```./gradlew bootRun```
4) You should now be able to access the backend at http://localhost:8080

# API endpoints

Here are the available API endpoints:

Meals Endpoint:
- ```GET /meals``` Get all meals
- ```GET /meals/{id}``` Get a meal by id
- ```POST /meals``` Add a new meal
- ```PUT /meals/{id}``` Update a meal by id
- ```DELETE /meals/{id}``` Delete a meal by id

Workouts Endpoint:
- ```GET /workouts``` Get all workouts
- ```GET /workouts/{id}``` Get a workout by id
- ```POST /workouts``` Add a new workout
- ```PUT /workouts/{id}``` Updates a workout by id
- ```DELETE /workouts/{id}``` Delete a workout by id

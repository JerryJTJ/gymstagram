This readme is to initialize the backend of gymstagram and gives a brief overivew of the APIs available. 

# Prerequisites
Before you can start the backend, you'll need to have the following tools installed:

- [Java Development Kit (JDK) 17](https://www.oracle.com/ca-en/java/technologies/downloads/#java17)
- [MongoDB](https://www.mongodb.com/try/download/community-kubernetes-operator)

Very important Note: Ask Jason or Matt to add your Connection IP Address to our IP Access List in our MongoDB 

# Getting Started

1) Clone this repository to your local machine.
2) - **Running locally:** To run the backend using a local database, add ```spring.data.mongodb.uri=mongodb://localhost:27017/test``` in ```gymstagram-backend\src\main\resources\application.properties```
   - **Running in prod:** Ask Matt or Jason for the access password and replace ```<pw>``` with the given password in ```spring.data.mongodb.uri=mongodb+srv://admin:<pw>@gymstagram.fjbdxhz.mongodb.net/gymstagram``` in ```gymstagram-backend\src\main\resources\application.properties```
3) Start the backend by runing the following command  ```./gradlew bootRun```
4) You should now be able to access the backend at http://localhost:8080. If Port 8080 already in use, add the following line in ```gymstagram-backend\src\main\resources\application.properties```, ```server.port = 8081```.


# Schemas

**User:**
```
{
   id: ID,
   username: String,
   password: String,
   email: String,
   following: List<String>,
   currentWeight: Float,
   targetWeight: Float
}
```

**LoginBody:**
```
{
   username: String,
   password: String
}
```

**Meal:**
```
{
   id: String,
   name: String,
   calories: Int,
   carbs: Int,
   protein: Int,
   fat: Int
}
```

**Workout:**
```
{
   id: String,
   name: String,
   reps: Int,
   sets: Int,
   weight: Int,
   duration: Int
}
```

**Post:**
```
{
   id: String,
   userId: String,
   description: String,
   // media: MultipartFile, (In progress)
   likes: Int,
   comments: List<Comment>
   timestamp: Long
}
```

**Comment:**
```
{
    id: String,
    userId: String,
    likes: Int,
    text: String,
    timestamp: Long
}
```

# API endpoints

Here are the available API endpoints:

**Users Endpoint:**

- ```GET /users``` Get all users
- ```GET /users/{id}``` Get a user by id
- ```POST /users``` Create a new user 
- ```POST /users/login``` Returns user if username/password combination found, null otherwise
- ```PUT /users/{followerId}/follow/{receiverId}``` Adds the receiverId to the User of the followerId's following array
- ```PUT /users/{followerId}/unfollow/{receiverId}``` Removes the receiverId of the User of the followerId's following array
- ```PUT /users/{id}``` Update a user by id
- ```DELETE /users/{id}``` Delete a user by id

**Meals Endpoint:**
- ```GET /meals``` Get all meals
- ```GET /meals/{id}``` Get a meal by id
- ```POST /meals``` Add a new meal
- ```PUT /meals/{id}``` Update a meal by id
- ```DELETE /meals/{id}``` Delete a meal by id

**Workouts Endpoint:**
- ```GET /workouts``` Get all workouts
- ```GET /workouts/{id}``` Get a workout by id
- ```POST /workouts``` Add a new workout
- ```PUT /workouts/{id}``` Update a workout by id
- ```DELETE /workouts/{id}``` Delete a workout by id

**Posts Endpoint:**
- ```GET /posts``` Get all posts
- ```GET /posts/{id}``` Get a post by id
- ```GET /posts/user/{userId}``` Get all posts by User id
- ```POST /posts``` Add a new post
- ```PUT /posts/{id}``` Update a post by id
- ```DELETE /posts/{id}``` Delete a post by id

   **Comment functionality:**

   - ```POST /posts/{id}/comment``` Add a new comment to an existing post with id
   - ```DELETE /posts/{id}/comment/{commentId}``` Delete a comment with commentId from a post with id

   **Like functionality:**

   - ```PUT /posts/{id}/like``` Like a post by id
   - ```PUT /posts/{id}/unlike``` Unlike a post by id

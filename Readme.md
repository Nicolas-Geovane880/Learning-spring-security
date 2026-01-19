# Learning Spring security

 This project simulates a college, which has students, class and teachers.
 
 The main reason for this project exists is to learn how the spring security environment works.
 
 This project uses basic auth (I tested the requests via Postman), that just authenticate the users with username (their email) and password.

 #### Authorization

 After the authentication happens, the system will determinate what who can do.

 Example: students as well as teachers do not need to authenticate to create their profile, but they have to be authenticated to look, update or delete their profile. Students can 
 not manipulate teachers' profile because they have distinct roles, and vice versa.

 ### Technologies used

 - Postman
 - MySQL 
 - Docker

 I used Docker-compose to integrate the application with a MySQL database image.

 #### How to run the application with Docker-compose (in case you decide to pull this project): 
 
 - Create the .env file and insert the database data in the variables environment according to the .env-example (or insert directly in the docker-compose.yml)
 - Open the terminal into the application root directory
 - Make sure that you have Docker Desktop installed and running
 - Type 'docker-compose up --build -d' 
 - Type 'docker ps' or 'docker container ps' to check if the containers are running
 - Send requests to the via browser/postman or another program (url: http://localhost/api/v1/teacher/find/1, since the application runs on the port 80, it is not necessary to inform the port in the url)

 If you want to run the application directly in the IDE, navigate to the Main class and click on the run button
 (In this case, the application will use the H2 database based on memory. Inform the port 8080 in the url: http://localhost:8080/api/v1/teacher/find/1)

 Next commits will conclude the project and implement new features soon.

 

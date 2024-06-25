1) After cloning the project, open docker app on your local machine
2) In terminal change your directory to the project directory and enter the "docker-compose up -d" command
3) This command pulls and runs kafka and mysql images, so there will be no need to install and run them locally
4) After run the application, default port is 8080
5) After successful build and run, perform this http request to make sure that the repository is empty: localhost:8080/message
6) Call localhost:8080/message/publish using this json as an example for your request body {"from":"Andranik","to":"Pavel","body":"Hello"}
7) Check the logs in your IDE, message is created, published to KAFKA topic called "messages", afterwards it is consumed and saved to db
8) Perform this http request to make sure that the item is saved: localhost:8080/message

# Geolocation Project
#### Test in the Local env

**Steps**
~~~~
- Clone the git repository from https://github.com/moniviru/geolocation.git
- Execute mvn clean install
- Execute mvn spring-boot:run -Djdk.tls.client.protocols=TLSv1.2
~~~~
The project will be deployed on localhost:8080

Load the geolocation file: (Attach a File)
~~~~
POST http://localhost:8080/geolocation/
~~~~
Find data by IP
~~~~
GET http://localhost:8080/geolocation/{ip}
~~~~
#### Test in the Remote env

You can also find these endpoints deployed on Heroku
~~~~
POST https://geolocation-monvrz.herokuapp.com/geolocation/
GET https://geolocation-monvrz.herokuapp.com/geolocation/{ip}
~~~~
#### Database

**Remote MongoDB**
~~~~
mongodb+srv://monvrz:<password>@cluster0.uo6re.mongodb.net/test?retryWrites=true&w=majority
~~~~

#### Project decisions

The project is based on a microservice that exposes two endpoints, the first one to upload and process the file and the second one to get the information related to an IP address. It was developed using Spring Boot because defining REST services there is easier and faster. I decided to connect with a No relational database because the idea is loading huge data in one table and it didn't make sense for me to do this in a relational database due to this process could be slower. I used mongoDB to save the data, and I implemented a composite key there. I tried to follow the SOLID principles developing the project. I used a usecase layer to provide the steps to follow for validating the data and a service layer to process the information according to the validations defined. I also used a Mongo Repository for accessing or saving the data.

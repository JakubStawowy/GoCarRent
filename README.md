# GoCarRent - Java Spring Boot + React Project 

## Description

GoCarRent is a monolithic client-server Java Spring Boot + React.js web application that allows users to rent their cars

## Technologies used

### Server

- Java 8
- MySql database
- Hibernate
- JWT authorization
- RabbitMQ

### Client
- HTML
- CSS
- Javascript
- React.js
- Material UI
  
## Licenses

## Configuration

1. Clone the repository:

```bash

git clone https://github.com/JakubStawowy/GoCarRent.git
```

2. Navigate to the server application directory:

```bash

cd ${rootDir}/GoCarRent/go-car-rent-spring-boot-application/
```
3. Configure the file src/main/resources/application.properties with your database properties and geocoding services api keys:
```properties

# database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.database=mysql
spring.datasource.url=jdbc:mysql://localhost:3306/go_car_rent_db
spring.datasource.username=root
spring.datasource.password=
```
4.Run the server application using command:
```bash

mvn spring-boot:run
```
5. Navigate to the client application repository:
```bash

cd ${rootDit}/GoCarRent/go-car-rent-react
```
7. Install dependencies:
```bash
npm install
```
9. Run the client application using command:
```bash

npm start
```

## Screenshots
![obraz](https://github.com/JakubStawowy/GoCarRent/assets/57110082/47d7d39a-b21a-48f0-8fbc-232aa73fcf91)

## Endpoints
- **User registration**
  - **Endpoint:** `/api/register`
  - **Method:** `POST`
  - **Body:**
    ```json
    {
      "name": "example_username",
      "phone": "example_phone_number",
      "email": "example@email.com",
      "password": "example_password"
    }
    ```
- **Login**
  - **Endpoint:** `/api/login`
  - **Method:** `POST`
  - **Body:**
    ```json
    {
      "email": "example@email.com",
      "password": "example_password"
    }
    ```
- **Get user details**
  - **Endpoint:** `/api/users/details`
  - **Method:** `GET`
  - **Body:**
    ```json
    {
      "userId": 1
    }
    ```
- **Get logged user details**
  - **Endpoint:** `/api/users/details/loggedUser`
  - **Method:** `GET`
  - **Request parameters:**
    - *empty*

- **Edit User:**
  - **Endpoint:** `/api/users/edit`
  - **Method:** `PUT`
  - **Body:**
    ```json
    {
      "name": "new_username",
      "phone": "new_phone_number",
      "password": "new_password",
      "email": "new_email@example.com"
    }
    ```

- **Get User Feedback:**
  - **Endpoint:** `/api/feedback/user`
  - **Method:** `GET`
  - **Request parameters:**
    - `userId` 

- **Add Feedback:**
  - **Endpoint:** `/api/feedback/add`
  - **Method:** `POST`
  - **Body:**
    ```json
    {
      "userId": "user_id",
      "content": "Feedback content",
      "rate": 5,
      "userId":  1,
      "authorId": 2
    }
    ```

- **Edit Feedback:**
  - **Endpoint:** `/api/edit-feedback`
  - **Method:** `PUT`
  - **Body:**
    ```json
    {
      "feedbackId": "feedback_id",
      "comment": "Updated feedback comment",
      "rating": 4
    }
    ```

- **Add announcement:**
  - **Endpoint:** `/api/announcements/save`
  - **Method:** `POST`
  - **Body:**
    ```json
    {
      "price": "price_example",
      "title": "title_example",
      "currency": "currency_example",
      "timeUnit": "time_unit_example",
      "carBrand": "car_brand_example",
      "carModel": "car_model_example",
      "authorId": 1
    }
    ```

- **Get All Announcements:**
  - **Endpoint:** `/api/announcements`
  - **Method:** `GET`
  - **Request parameters:**
    - *empty*

- **Filter Announcements:**
  - **Endpoint:** `/api/announcements/filter`
  - **Method:** `GET`
  - **Request parameters:**
    - `priceFrom`
    - `priceTo`
    - `title`
    - `currency`
    - `timeUnit`
    - `carBrand`
    - `carModel`

- **Get Announcement Details:**
  - **Endpoint:** `/api/announcements`
  - **Method:** `GET`
  - **Request parameters:**
    - `announcementId`

- **Delete Announcement:**
  - **Endpoint:** `/api/orders/delete`
  - **Method:** `DELETE`
  - **Request parameters:**
    - `announcementId`

- **Send Request For Rent Consent:**
  - **Endpoint:** `/api/rent/consent/request`
  - **Method:** `POST`
  - **Request parameters:**
    - `announcementId`

- **Send Response For Rent Consent:**
  - **Endpoint:** `/api/delivery/consent/response`
  - **Method:** `POST`
  - **Request parameters:**
    - `announcementId`
    - `tenantId`
    - `consent`

- **Send Request For Rent Realization:**
  - **Endpoint:** `/api/rent/realization/request`
  - **Method:** `POST`
  - **Request parameters:**
    - `announcementId`

- **Send Response For Rent Realization:**
  - **Endpoint:** `/api/delivery/realization/response`
  - **Method:** `POST`
  - **Request parameters:**
    - `announcementId`
    - `tenantId`

- **Get Messages:**
  - **Endpoint:** `/api/messages/sent`
  - **Method:** `GET`
  - **Request parameters:**
    - *empty*

- **Get Received Messages:**
  - **Endpoint:** `/api/messages/received`
  - **Method:** `GET`
  - **Request parameters:**
    - *empty*

## Authors
- **Jakub Stawowy** (stawowykuba@gmail.com)

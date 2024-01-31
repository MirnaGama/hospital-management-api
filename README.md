# Hospital Management - API Module ![Build Status](https://github.com/MirnaGama/hospital-management-api/actions/workflows/maven.yml/badge.svg)

## About the project
Hospital Management API built in Spring Boot

### Prerequisites:
- Spring Boot 3.2.1 
- JDK 17
- Maven 4.0.0

### Running the application
1. `git clone https://github.com/MirnaGama/hospital-management-api/`
2. `cd hospital-management-api`
3. `mvn clean install`<br>
It will build the jar file in the target folder
4. `mvn spring-boot:run`<br>
It will compile and run the application on default port (8080)

### Running tests
- `mvn test`<br>
It will executes all the tests.

- `mvn -Dtest=packageName.className test`<br>
It will execute only one test class

- `mvn -Dtest=packageName.className#methodName test`<br>
It will run only one test method from one test class

### Features - v1.0
- [X] R1 - Doctor Registration
- [X] R2 - List of Doctors
- [X] R3 - Doctor Update
- [X] R4 - Doctor Exclusion
- [X] R5 - Patient Registration
- [X] R6 - List of Patients
- [X] R7 - Patient Update
- [X] R8 - Patient Exclusion
- [X] R9 - Consultation Scheduling
- [X] R10 - Consultation Cancellation

## API Documentation - /swagger-ui/index.html

### authentication

#### POST - [**/api/auth/register**] - Register a new user

- **Body:**
```
{   
    "login" (string, required),  
    "password" (string, required), 
}
```

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |

#### POST - [**/api/auth/login**] - Perform the login

- **Body:**
```
{   
    "login" (string, required),  
    "password" (string, required), 
}
```

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |
| `403` | _Incorrect credentials_ | 


### doctors

#### POST - [**/api/v1.0/doctors**] - Adds a new doctor

- **Body:**
```
{   
    "name" (string, required),  
    "email" (string, required),  
    "crm" (string, required),  
    "telephone" (string, required), 
    "specialty" (string, required), 
    "address": {   
        "street" (string, required),
        "neighborhood" (string, required), 
        "zipCode" (string, required),  
        "city" (string, required),  
        "state" (string, required),
        "additionalDetails" (string, optional),  
        "houseNumber" (string, optional)
    } 
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `201` | _Successfully created_ |
| `400` | _Validation Error_ |
| `403` | _Unauthorized / Invalid token_ | 

#### GET - [**/api/v1.0/doctors/{id}**] - Get an existing doctor

- **Response Body Example:**
```
{
    "id": 1,
    "name": "DOCTOR TEST",
    "email": "test@gmail.com",
    "crm": "12456",
    "telephone": "(81) 99999999",
    "specialty": "ORTHOPEDICS",
    "active": true,
    "address": {
        "street": "TEST STR.",
        "neighborhood": "TEST NEIGHBORHOOD",
        "zipCode": "12345678",
        "city": "TEST CITY",
        "state": "ST",
        "additionalDetails": null,
        "houseNumber": null
    }
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `id` | _Unique identifier of the doctor who will be fetched_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `404` | _Entity not found_ |
| `403` | _Unauthorized / Invalid token_ | 

#### GET - [**/api/v1.0/doctors**] - Get a list of doctors

- **Response Body Example:**
```
{
    "content": [
        {
            "name": "Test1",
            "email": "test1@gmail.com",
            "crm": "123456",
            "specialty": "ORTHOPEDICS"
        },
        {
            "name": "Test2",
            "email": "test2@gmail.com",
            "crm": "789101",
            "specialty": "ORTHOPEDICS"
        },
        {
            "name": "Test3",
            "email": "test3@gmail.com",
            "crm": "112131",
            "specialty": "ORTHOPEDICS"
        },
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 3,
    "last": true,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "number": 0,
    "size": 10,
    "first": true,
    "numberOfElements": 3,
    "empty": false
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `size` | _Number of records that should be returned_ |
| `sort` | _Sort by object attribute in descending order_ |
| `page` | _Page number_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `403` | _Unauthorized / Invalid token_ | 


#### PUT - [**/api/v1.0/doctors**] - Updates an existing doctor

- **Body:**
```
{   
    "id" (number, required),
    "name" (string, optional), 
    "telephone" (string, optional),  
    "address": {   
        "street" (string, optional),
        "neighborhood" (string, optional), 
        "zipcode" (string, optional),  
        "city" (string, optional),  
        "state" (string, optional),
        "additionalDetails" (string, optional),  
        "houseNumber" (string, optional),
    } 
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |
| `403` | _Unauthorized / Invalid token_ | 


#### DELETE - [**/api/v1.0/doctors/{id}**] - Deactivates an existing doctor

- **Response Body Example:**
```
{
    "id": 2,
    "name": "DEACTIVATED DOCTOR TEST",
    "email": "test@gmail.com",
    "crm": "12456",
    "telephone": "(81) 99999999",
    "specialty": "ORTHOPEDICS",
    "active": false,
    "address": {
        "street": "TEST STR.",
        "neighborhood": "TEST NEIGHBORHOOD",
        "zipCode": "12345678",
        "city": "TEST CITY",
        "state": "ST",
        "additionalDetails": null,
        "houseNumber": null
    }
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `id` | _Unique identifier of the doctor who will be deactivated_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |
| `404` | _Entity not found_ |
| `403` | _Unauthorized / Invalid token_ | 

### patients

#### POST - [**/api/v1.0/patients**] - Adds a new patient

- **Body:**
```
{   
    "name" (string, required),  
    "email" (string, required),  
    "cpf" (string, required),  
    "telephone" (string, required), 
    "address": {   
        "street" (string, required),
        "neighborhood" (string, required), 
        "zipCode" (string, required),  
        "city" (string, required),  
        "state" (string, required),
        "additionalDetails" (string, optional),  
        "houseNumber" (string, optional)
    } 
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `201` | _Successfully created_ |
| `400` | _Validation Error_ |
| `403` | _Unauthorized / Invalid token_ | 


#### GET - [**/api/v1.0/patients/{id}**] - Get an existing patient

- **Response Body Example:**
```
{
    "id": 1,
    "name": "PATIENT TEST",
    "email": "test@gmail.com",
    "cpf": "11111111111",
    "telephone": "(81) 99999999",
    "active": true,
    "address": {
        "street": "TEST STR.",
        "neighborhood": "TEST NEIGHBORHOOD",
        "zipCode": "12345678",
        "city": "TEST CITY",
        "state": "ST",
        "additionalDetails": null,
        "houseNumber": null
    }
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `id` | _Unique identifier of the patient who will be fetched_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `404` | _Entity not found_ |
| `403` | _Unauthorized / Invalid token_ | 

#### GET - [**/api/v1.0/patients**] - Get a list of patients

- **Response Body Example:**
```
{
    "content": [
        {
            "name": "Test1",
            "email": "test1@gmail.com",
            "cpf": "123456"
        },
        {
            "name": "Test2",
            "email": "test2@gmail.com",
            "cpf": "789101"
        },
        {
            "name": "Test3",
            "email": "test3@gmail.com",
            "cpf": "112131"
        },
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 3,
    "last": true,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "number": 0,
    "size": 10,
    "first": true,
    "numberOfElements": 3,
    "empty": false
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `size` | _Number of records that should be returned_ |
| `sort` | _Sort by object attribute in descending order_ |
| `page` | _Page number_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `403` | _Unauthorized / Invalid token_ | 

#### PUT - [**/api/v1.0/patients**] - Updates an existing patient

- **Body:**
```
{   
    "id" (number, required),
    "name" (string, optional), 
    "telephone" (string, optional),  
    "address": {   
        "street" (string, optional),
        "neighborhood" (string, optional), 
        "zipcode" (string, optional),  
        "city" (string, optional),  
        "state" (string, optional),
        "additionalDetails" (string, optional),  
        "houseNumber" (string, optional),
    } 
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |
| `403` | _Unauthorized / Invalid token_ | 

#### DELETE - [**/api/v1.0/patients/{id}**] - Deactivates an existing patient

- **Response Body Example:**
```
{
    "id": 1,
    "name": "DEACTIVATED PATIENT TEST",
    "email": "test@gmail.com",
    "cpf": "11111111111",
    "telephone": "(81) 99999999",
    "active": false,
    "address": {
        "street": "TEST STR.",
        "neighborhood": "TEST NEIGHBORHOOD",
        "zipCode": "12345678",
        "city": "TEST CITY",
        "state": "ST",
        "additionalDetails": null,
        "houseNumber": null
    }
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `id` | _Unique identifier of the patient who will be deactivated_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |
| `404` | _Entity not found_ |
| `403` | _Unauthorized / Invalid token_ | 

### consultations

#### POST - [**/api/v1.0/consultations**] - Adds a new consultation

- **Body:**
```
{   
    "patientId" (number, required),  
    "consultationDate" (string, required),  
    "doctorId" (number, required if _specialty_ field is not filled),
    "specialty" (string, required if _doctorId_ field is not filled)
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `201` | _Successfully created_ |
| `400` | _Validation Error_ |
| `403` | _Unauthorized / Invalid token_ | 
| `404` | _Entity not found_ |

#### GET - [**/api/v1.0/consultations/{id}**] - Get an existing consultation

- **Response Body Example:**
```
{
    "id": 1,
    "consultationDate": "22/04/2024 10:34",
    "patient": {...},
    "doctor": {...},
    "canceled": false,
    "reasonCancellation: ""
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Request Parameters:**

| Key  | Description |
| ------------- | ------------- |
| `id` | _Unique identifier of the consultation that will be fetched_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `404` | _Entity not found_ |
| `403` | _Unauthorized / Invalid token_ | 

#### DELETE - [**/api/v1.0/consultations**] - Cancels a scheduled consultation

- **Body:**
```
{   
    "consultationId" (number, required),  
    "reasonCancellation" (string, required),  
}
```

- **Request Headers:**

| Key  | Description |
| ------------- | ------------- |
| `Authorization` | _Authorization token_ |

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `403` | _Unauthorized / Invalid token_ | 
| `404` | _Entity not found_ |

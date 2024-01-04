# Hospital Management - API Module ![Build Status](https://github.com/MirnaGama/hospital-management-api/actions/workflows/maven.yml/badge.svg)

## About the project
Hospital Management API built in Spring Boot

### Prerequisites:
- Spring Boot 3.2.1 
- JDK 17
- Maven 4.0.0

### Features
- [X] R1 - Doctor Registration
- [ ] R2 - List of Doctors
- [ ] R3 - Doctor Update
- [ ] R4 - Doctor Exclusion
- [ ] R5 - Patient Registration
- [ ] R6 - List of Patients
- [ ] R7 - Patient Update
- [ ] R8 - Patient Exclusion
- [ ] R9 - Consultation Scheduling
- [ ] R10 - Consultation Cancellation

## API Documentation

### /doctors

POST - [**/api/v1.0/doctors**] - Adds a new doctor

- **Body:**
```
{   
    "name" (string, required),  
    "email" (string, required),  
    "crm" (string, required),  
    "telephone" (string, required),  
    "address": {   
        "street" (string, required),
        "neighborhood" (string, required), 
        "zipcode" (string, required),  
        "city" (string, required),  
        "state" (string, required),
        "additionalDetails" (string, optional),  
        "houseNumber" (string, optional)
    } 
}
```

- **Responses:**

| Code  | Description |
| ------------- | ------------- |
| `200` | _Successful operation_ |
| `400` | _Validation Error_ |

GET - [**/api/v1.0/doctors**] - Get a list of doctors

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




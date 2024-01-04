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





# Spring CRUD API Documentation

This repository contains the documentation for the Spring CRUD API, which provides endpoints to manage pets, people, and users.
## Table of Contents

- [Pet Endpoints](#pet-endpoints)
  - [Create Pet](#create-pet)
  - [All Pets](#all-pets)
  - [Find Pet by Slug](#find-pet-by-slug)
  - [Update Pet](#update-pet)
  - [Delete Pet](#delete-pet)

- [Person Endpoints](#person-endpoints)
  - [Create Person](#create-person)
  - [All People](#all-people)
  - [Find Person by Slug](#find-person-by-slug)
  - [Update Person](#update-person)
  - [Delete Person](#delete-person)

- [User Endpoints](#user-endpoints)
  - [SignIn](#signin)
  - [LogIn](#login)
  - [All Users](#all-users)
  - [Find User](#find-user)

## Pet Endpoints

### Create Pet
- **URL**: `http://localhost:8080/pets`
- **Method**: POST
- **Description**: create new pet

- **Request Body**:
```http

{
    "name": "Duke",
    "age": 2,
    "race": "Golden Retriever",
    "species": "Dog",
    "person": {
        "slug": "john-doe"
    }
}
```
- **Response**: HTTP 200 OK
```http
{
    "name": "Duke",
    "age": 2,
    "race": "Golden Retriever",
    "species": "Dog",
    "slug": "duke"
}
```
  
### All Pets
- **URL**: `http://localhost:8080/pets`
- **Method**: GET
- **Description**: create new pet
- **Response**: HTTP 200 OK
```http
[
    {
        "name": "Milo",
        "age": 2,
        "race": "Ragdoll",
        "species": "Cat",
        "slug": "milo"
    },
    {
        "name": "Oliver",
        "age": 1,
        "race": "Siberian Husky",
        "species": "Dog",
        "slug": "oliver"
    },
    {
        "name": "Coco",
        "age": 6,
        "race": "Persian",
        "species": "Cat",
        "slug": "coco"
    }
]
```
### Find Pet by Slug

- **URL**: `http://localhost:8080/pets/{slug}`
- **Method**: GET
- **Description**: 
- **Response**: HTTP 200 OK
```http
{
    "name": "Oliver",
    "age": 1,
    "race": "Siberian Husky",
    "species": "Dog",
    "slug": "oliver"
}
```
### Update Pet

- **URL**: `http://localhost:8080/pets/{slug}`
- **Method**: PUT
- **Description**: 
- **Request Body**:
```http
 {
    "name": "Duke",
    "age": 3,
    "race": "Golden Retriever",
    "species": "Dog",
    "person": {
      "slug": "john-doe"
    }
  }
```
- **Response**: HTTP 200 OK
```http
{
    "name": "Duke",
    "age": 3,
    "race": "Golden Retriever",
    "species": "Dog",
    "slug": "duke"
}
```
### Delete Pet

- **URL**: `http://localhost:8080/pets/{slug}`
- **Method**: DELETE
- **Description**: 
- **Response**: HTTP 200 OK
```text
Pet successfully deleted
```
- **Response**: HTTP 404 NOT FOUND
```text
The pet does not exist
```
## Person Endpoints
### Create Person

- **URL**: `http://localhost:8080/people`
- **Method**: POST
- **Description**:
- **Request Body**:
```http
{
    "name": "Bob Johnson",
    "date": "1995-03-10",
    "phone": "555-123-4567",
    "direction": "789 Elm Street, Village, Country",
    "email": "bob.johnson@example.com"
}

```
- **Response**: HTTP 200 OK
 ```http
{
    "slug": "bob-johnson",
    "name": "Bob Johnson",
    "date": "1995-03-10",
    "email": "bob.johnson@example.com",
    "phone": "555-123-4567",
    "direction": "789 Elm Street, Village, Country"
}
```
- **Response**: HTTP 400 BAD_REQUEST
 ```text
The person already exists

```
### All People

- **URL**: `http://localhost:8080/people`
- **Method**: GET
- **Description**:
- **Response**: HTTP 200 OK
 ```http
[
    {
        "slug": "alice-smith",
        "name": "Alice Smith",
        "date": "1985-11-28",
        "email": "alice.smith@example.com",
        "phone": "987-654-3210",
        "direction": "456 Park Avenue, Town, Country"
    },
    {
        "slug": "john-doe",
        "name": "John Doe",
        "date": "1990-05-15",
        "email": "john.doe@example.com",
        "phone": "123-456-7890",
        "direction": "123 Main Street, City, Country"
    },
    {
        "slug": "bob-johnson",
        "name": "Bob Johnson",
        "date": "1995-03-10",
        "email": "bob.johnson@example.com",
        "phone": "555-123-4567",
        "direction": "789 Elm Street, Village, Country"
    }
]
```
### Find Person by Slug

- **URL**: `http://localhost:8080/people/{slug}`
- **Method**: GET
- **Description**:
- **Response**: HTTP 200 OK
 ```http
{
    "slug": "alice-smith",
    "name": "Alice Smith",
    "date": "1985-11-28",
    "email": "alice.smith@example.com",
    "phone": "987-654-3210",
    "direction": "456 Park Avenue, Town, Country",
    "pets": [
        {
            "name": "Milo",
            "age": 2,
            "race": "Ragdoll",
            "species": "Cat",
            "slug": "milo"
        },
        {
            "name": "Oliver",
            "age": 1,
            "race": "Siberian Husky",
            "species": "Dog",
            "slug": "oliver"
        },
        {
            "name": "Coco",
            "age": 6,
            "race": "Persian",
            "species": "Cat",
            "slug": "coco"
        }
    ]
}
```
- **Response**: HTTP 404 NOT FOUND
```text
The person does not exist
```
### Update Person

- **URL**: `http://localhost:8080/people/{slug}`
- **Method**: PUT
- **Description**:
- **Response**: HTTP 200 OK
- **Request Body**:
```http
{
  "name": "Bob Johnson",
  "date": "1995-03-101",
  "phone": "555-123-4567",
  "direction": "789 Elm Street, Village, Country",
  "email": "bob.johnson@example.com"
}
```
- **Response**: HTTP 200 OK
```http
{
    "slug": "bob-johnson",
    "name": "Bob Johnson",
    "date": "1995-03-101",
    "email": "bob.johnson@example.com",
    "phone": "555-123-4567",
    "direction": "789 Elm Street, Village, Country"
}
```
- **Response**: HTTP 404 NOT FOUND
```text
The person does not exist
```
### Delete Person

- **URL**: `http://localhost:8080/people/{slug}`
- **Method**: DELETE
- **Description**:
- **Response**: HTTP 200 OK
```text
Person deleted successfully
```
- **Response**: HTTP 404 NOT FOUND
```text
The person does not exist
```
## User Endpoints
### SignIn

- **URL**: `http://localhost:8080/users`
- **Method**: POST
- **Description**:
- **Request Body**:
```http
{
    "username": "admin_007",
    "password": "adminpass!@#",
    "rol": "admin",
    "email": "admin@example.com"
}

```
- **Response**: HTTP 200 OK

```http
{
    "username": "admin_007",
    "password": "adminpass!@#",
    "rol": "admin",
    "email": "admin@example.com"
}
```
- **Response**: HTTP 400 BAD_REQUEST
```text
username already exist
```
### LogIn

- **URL**: `http://localhost:8080/users/login`
- **Method**: POST
- **Description**:
- **Request Body**:
```http
{
    "username": "mark_johnson",
    "password": "markpass456"
}

```
- **Response**: HTTP 200 OK

```http
{
    "status": 200,
    "message": "mark_johnson",
    "url": "/users/login",
    "date": "2023-07-27 13:25:58"
}
```
- **Response**: HTTP 404 NOT FOUND
```http
{
    "status": 404,
    "message": "The credentials are wrong",
    "url": "/users/login",
    "date": "2023-07-27 13:26:44"
}
```
### All Users

- **URL**: `http://localhost:8080/users`
- **Method**: GET
- **Description**:
- **Response**: HTTP 200 OK
```http
[
    {
        "username": "mark_johnson",
        "email": "mark.johnson@example.com",
        "rol": "user"
    },
    {
        "username": "jane_smith",
        "email": "jane.smith@example.com",
        "rol": "user"
    },
    {
        "username": "susan_brown",
        "email": "susan.brown@example.com",
        "rol": "user"
    },
    {
        "username": "admin_007",
        "email": "admin@example.com",
        "rol": "admin"
    }
]
```
### Find User

- **URL**: `http://localhost:8080/users/{slug}`
- **Method**: GET
- **Description**:
- **Response**: HTTP 200 OK
```http
{
    "username": "jane_smith",
    "email": "jane.smith@example.com",
    "rol": "user"
}
```
- **Response**: HTTP 404 NOT FOUND
```text
Username does not exist
```


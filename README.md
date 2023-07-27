
# Spring CRUD API Documentation

This repository contains the documentation for the Spring CRUD API, which provides endpoints to manage pets, people, and users.

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

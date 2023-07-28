# Task Management CRUD REST API with MongoDB Atlas

## Introduction
This is a simple CRUD (Create, Read, Update, Delete) REST API for managing tasks. It is built using Java with Spring Boot, and it uses MongoDB Atlas as the database to store the tasks.

## Prerequisites
Before running the application, you need to have the following installed on your system:
- Java Development Kit (JDK) 8 or higher
- Maven (for building and running the application)
- MongoDB Atlas account (to set up the database)

## Collection api to add Task

to add the task 
Postmapping

http://localhost:8080/tasks

request-> 
 {
     "description": "onboarding",
     "severity": 8,
     "assigne": "mahesh",
     "storyPoint": 12
 }

 response-> 
 {
    "taskId": "fadc1705",
    "description": "onboarding",
    "severity": 8,
    "assigne": "mahesh",
    "storyPoint": 12
}
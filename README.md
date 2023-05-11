# Volunteer_Management_System_REST_API_Spring_Boot

## Steps to test the API Routes using Swagger

1. RUN "docker run -p 8080:8080 goodwill80/vms-springboot:v1.0.0"
2. Go to https://delicate-kangaroo-fe57dd.netlify.app/ to generate a bearer token from Firebase, and copy the token to clipboard.
3. Go to http://localhost:8080/swagger-ui/index.html and paste the token in Bearer Authorization.
4. You can proceed to test the routes.

## Description

### Relational Diagrams

![My Image](RelationalDiagrams.png)

### Authentication & Authorization Flow between front-end and backend

1. Using Firebase Auth JWT for authentication and authorization.
2. Setup Security Filter Chain and Firbase filter to verify all requests which require JWT token.

![My Image](Auth_process.png)


## Important to note before using the app:

## Team

- [Jonathan](https://github.com/goodwill80 "jonathan's github")
- [Ace](https://github.com/acetay "ace's github")
- [Yingwang](https://github.com/shiywsg "yingwang's github")
- [Claire](https://github.com/clairetkw "claire's github")

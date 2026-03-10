# User API - JWT Secured REST Endpoints

## Project Structure

```
user-api/
??? src/main/java/com/corpay/userapi/
?   ??? config/
?   ?   ??? RestApplication.java          # JAX-RS configuration
?   ??? controller/
?   ?   ??? AuthController.java           # Login endpoint
?   ?   ??? ResourceController.java       # Secured REST endpoints
?   ??? model/
?   ?   ??? LoginRequest.java
?   ?   ??? LoginResponse.java
?   ??? security/
?       ??? Secured.java                  # Custom @Secured annotation
?       ??? JwtUtil.java                  # JWT token utility
?       ??? JwtAuthenticationFilter.java  # JWT authentication filter
??? src/main/webapp/WEB-INF/
    ??? beans.xml                         # CDI configuration
```

## Features

? **JWT Authentication** - Secure token-based authentication
? **@Secured Annotation** - Easy-to-use security annotation for REST endpoints
? **Jakarta EE 10** - Using latest Jakarta EE standards
? **RESTful API** - Full CRUD operations
? **Container-managed** - CDI and dependency injection

## API Endpoints

### 1. Authentication (Public)

#### Login - Get JWT Token
```
POST /api/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "password"
}

Response (200 OK):
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "testuser",
  "message": "Login successful"
}
```

### 2. Resources (Secured - Requires JWT)

All endpoints under `/api/resources` require JWT token in Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

#### Get All Resources
```
GET /api/resources
Authorization: Bearer <token>

Response (200 OK):
{
  "message": "Successfully retrieved resources",
  "authenticatedUser": "testuser",
  "resources": ["Resource 1", "Resource 2", "Resource 3"]
}
```

#### Get Resource by ID
```
GET /api/resources/{id}
Authorization: Bearer <token>

Response (200 OK):
{
  "id": "123",
  "name": "Resource 123",
  "description": "This is a secured resource",
  "accessedBy": "testuser"
}
```

#### Create Resource
```
POST /api/resources
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "New Resource",
  "description": "Resource description"
}

Response (201 Created):
{
  "message": "Resource created successfully",
  "createdBy": "testuser",
  "resource": { ... }
}
```

#### Update Resource
```
PUT /api/resources/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Updated Resource",
  "description": "Updated description"
}

Response (200 OK):
{
  "message": "Resource updated successfully",
  "id": "123",
  "updatedBy": "testuser",
  "resource": { ... }
}
```

#### Delete Resource
```
DELETE /api/resources/{id}
Authorization: Bearer <token>

Response (200 OK):
{
  "message": "Resource deleted successfully",
  "id": "123",
  "deletedBy": "testuser"
}
```

#### Get Current User Info
```
GET /api/resources/me
Authorization: Bearer <token>

Response (200 OK):
{
  "username": "testuser",
  "message": "You are authenticated!",
  "timestamp": 1234567890
}
```

## Testing with cURL

### 1. Login and get token
```bash
curl -X POST http://localhost:8080/user-api/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password"}'
```

### 2. Use token to access secured endpoint
```bash
# Save token from login response
TOKEN="eyJhbGciOiJIUzI1NiJ9..."

# Access secured endpoint
curl -X GET http://localhost:8080/user-api/api/resources \
  -H "Authorization: Bearer $TOKEN"
```

### 3. Try without token (should fail with 401)
```bash
curl -X GET http://localhost:8080/user-api/api/resources
```

## Testing with Postman

1. **Login:**
   - Method: POST
   - URL: `http://localhost:8080/user-api/api/auth/login`
   - Body (JSON):
     ```json
     {
       "username": "testuser",
       "password": "password"
     }
     ```
   - Copy the `token` from response

2. **Access Secured Endpoint:**
   - Method: GET
   - URL: `http://localhost:8080/user-api/api/resources`
   - Headers:
     - `Authorization`: `Bearer <paste-token-here>`

## Security Configuration

### JWT Settings (JwtUtil.java)
- **Secret Key**: 256-bit secret (change in production!)
- **Expiration**: 24 hours
- **Algorithm**: HMAC SHA-256

?? **IMPORTANT**: Change the SECRET_KEY in production and load it from secure configuration!

### Current Authentication Logic
For demo purposes, the login accepts:
- **Any username** with password: `password`

Replace this in `AuthController.java` with actual database lookup and password verification.

## Build and Deploy

### Build WAR file
```bash
mvn clean package
```

### Deploy
Deploy the generated `target/user-api.war` to your Jakarta EE application server:
- WildFly
- Payara
- TomEE
- Open Liberty

## Error Responses

### 401 Unauthorized
```json
{
  "error": "Missing or invalid Authorization header"
}
```

### 400 Bad Request
```json
{
  "error": "Username and password are required"
}
```

## How It Works

1. **@Secured Annotation**: Custom `@NameBinding` annotation marks endpoints requiring authentication
2. **JwtAuthenticationFilter**: Intercepts requests to `@Secured` endpoints
3. **Token Validation**: Filter validates JWT token and extracts username
4. **Security Context**: Username is stored in request context for use in controllers
5. **Access Granted**: Valid token allows access to secured endpoints

## Next Steps

- [ ] Integrate with DAO library for user authentication
- [ ] Add role-based access control (RBAC)
- [ ] Implement refresh tokens
- [ ] Add password encryption (BCrypt)
- [ ] Configure CORS for cross-origin requests
- [ ] Add request/response logging
- [ ] Implement API versioning

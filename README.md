# Task Manager Application

A full-stack task management application with user authentication, CRUD operations, filtering, and search functionality.

## Features

- **User Authentication**: Register and login with JWT-based authentication
- **Task Management**: Create, Read, Update, and Delete tasks
- **Filtering**: Filter tasks by status and priority
- **Search**: Search tasks by title and description
- **Modern UI**: Built with React, TypeScript, Tailwind CSS
- **Secure Backend**: Spring Boot with MySQL and JWT authentication

## Tech Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security
- MySQL
- JWT Authentication
- Maven

### Frontend
- React 18
- TypeScript
- Vite
- Tailwind CSS
- React Router
- Axios

## Prerequisites

- Java 17 or higher
- Maven
- Node.js 18+
- MySQL 8+
- npm or yarn

## Setup Instructions

### 1. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE taskmanager;
```

Or the application will automatically create the database on first run.

### 2. Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Update database credentials in `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    username: your_username
    password: your_password
```

3. Build and run the application:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will run on `http://localhost:8080`

### 3. Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will run on `http://localhost:5173`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login user

### Tasks
- `GET /api/tasks` - Get all tasks for authenticated user
- `GET /api/tasks/{id}` - Get task by ID
- `POST /api/tasks` - Create a new task
- `PUT /api/tasks/{id}` - Update a task
- `DELETE /api/tasks/{id}` - Delete a task
- `GET /api/tasks/filter/status?status={status}` - Filter by status
- `GET /api/tasks/filter/priority?priority={priority}` - Filter by priority
- `GET /api/tasks/search?query={query}` - Search tasks

## Usage

1. Register a new account or login
2. Create tasks with title, description, status, priority, and due date
3. Use the search bar to find specific tasks
4. Filter tasks by status (Pending, In Progress, Completed, Cancelled)
5. Filter tasks by priority (Low, Medium, High)
6. Edit or delete tasks as needed

## Project Structure

```
Task-Manager/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/taskmanager/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   ├── security/
│   │   │   │   ├── service/
│   │   │   │   └── util/
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── api/
│   │   ├── components/
│   │   ├── contexts/
│   │   ├── pages/
│   │   └── types/
│   └── package.json
└── README.md
```

## Development

### Running in Development Mode

Backend:
```bash
cd backend && mvn spring-boot:run
```

Frontend:
```bash
cd frontend && npm run dev
```

### Building for Production

Backend:
```bash
cd backend && mvn clean package
java -jar target/task-manager-api-1.0.0.jar
```

Frontend:
```bash
cd frontend && npm run build
```

The built files will be in `frontend/dist`

## License

MIT




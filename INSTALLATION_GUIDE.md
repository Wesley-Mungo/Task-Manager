# Installation Guide

## Requirements

Before running the application, you need to install:

1. **Java JDK 17 or higher**
2. **MySQL** (database)

Maven is optional - the project includes Maven Wrapper.

## Step-by-Step Installation

### 1. Install Java JDK 17

**Option A: Manual Installation (Recommended)**

1. Download Java 17 from: https://adoptium.net/temurin/releases/?version=17
2. Run the installer
3. After installation, verify it's working:
   ```powershell
   java -version
   ```

**Option B: Using winget (if you have admin rights)**

Open PowerShell as Administrator and run:
```powershell
winget install --source winget EclipseAdoptium.Temurin.17.JDK --accept-package-agreements --accept-source-agreements
```

### 2. Install MySQL

**Option A: Download from official site**
1. Go to: https://dev.mysql.com/downloads/mysql/
2. Download MySQL Installer for Windows
3. Run the installer and follow the setup wizard
4. Set your root password (we'll update this in the config)

**Option B: Using winget**
```powershell
winget install -e --id MySQL.MySQL-server-8.0
```

### 3. Verify Installation

After installing Java, open a NEW PowerShell window and run:

```powershell
java -version
```

You should see output like:
```
openjdk version "17.0.x"
```

## Running the Application

### Option 1: Using Maven Wrapper (Easiest - No need to install Maven globally)

Once Java is installed:

```powershell
cd backend
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

### Option 2: Install Maven and Use it

If you want to install Maven globally:

1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract the zip file to a location like `C:\Program Files\Apache\Maven`
3. Add Maven to your PATH:
   - Press `Win + X` and select "System"
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Under "System variables", find "Path" and click "Edit"
   - Click "New" and add: `C:\Program Files\Apache\Maven\bin`
   - Click OK and restart your terminal
4. Then you can use:
   ```powershell
   mvn clean install
   mvn spring-boot:run
   ```

### Option 3: Use an IDE (Easiest for beginners)

Install **IntelliJ IDEA** (recommended) or **Eclipse**:
- IntelliJ: https://www.jetbrains.com/idea/download/
- Eclipse: https://www.eclipse.org/downloads/

Both IDEs include Maven support and will help you run the application.

## Running the Frontend

The frontend is much simpler:

```powershell
cd frontend
npm install
npm run dev
```

## Quick Start Summary

Once Java 17 and MySQL are installed:

**Terminal 1 - Backend:**
```powershell
cd C:\Users\Dell\Desktop\Task-Manager\backend
.\mvnw.cmd spring-boot:run
```

**Terminal 2 - Frontend:**
```powershell
cd C:\Users\Dell\Desktop\Task-Manager\frontend
npm install
npm run dev
```

Then visit: http://localhost:5173

## Troubleshooting

### "JAVA_HOME not found"
Make sure Java is installed and the JAVA_HOME environment variable is set:
```powershell
setx JAVA_HOME "C:\Program Files\Java\jdk-17"
```

### "Port 8080 already in use"
Another application is using port 8080. Either:
- Stop the other application
- Or change the port in `backend/src/main/resources/application.yml`

### Database connection issues
Check your MySQL credentials in `backend/src/main/resources/application.yml`




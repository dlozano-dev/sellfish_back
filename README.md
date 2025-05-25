# 🌱 Sellfish Backend

This is a basic backend application built with **Spring Boot**, using **Maven** as the build tool and running on **Java 21**.

---

## 🛠️ Technologies Used

- Java 21 (JDK 21)
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA (optional)
- H2 / MySQL / PostgreSQL (configurable)
- Lombok (optional)

---

## 🚀 Getting Started

### ✅ Prerequisites

Make sure you have the following installed:

- [Java JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)
- IDE (e.g., IntelliJ IDEA, Eclipse)

---

### 📦 Installation

1. **Clone the repository:**
```bash
git clone https://github.com/yourusername/your-repo-name.git
cd your-repo-name
```
### 🧱 Build and Run

### Build the project with Maven:
```bash
mvn clean install
```
Run the application:
```bash
mvn spring-boot:run
```

The application will start at:
```bash
http://localhost:8080
```

## ⚙️ Configuration

Edit the `src/main/resources/application.properties` file to configure your application's settings.

### Example (for H2 database):
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

### Example (for MySQL):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
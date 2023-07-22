plugins {
  java
  id("org.springframework.boot") version "3.1.0"
  id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

extra["springCloudVersion"] = "2022.0.3"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
  implementation("com.auth0:java-jwt:4.4.0")
  implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.1")
  implementation("com.mysql:mysql-connector-j:8.0.33") 
  compileOnly("org.projectlombok:lombok") 
  annotationProcessor("org.projectlombok:lombok")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}  

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

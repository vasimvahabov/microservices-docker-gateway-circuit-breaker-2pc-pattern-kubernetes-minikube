plugins {
  java
  id("org.springframework.boot") version "3.1.1"
  id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java{
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
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.liquibase:liquibase-core:4.16.1")
  implementation("com.mysql:mysql-connector-j:8.0.33") 
  implementation("io.github.resilience4j:resilience4j-feign:2.0.2")
  implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
  implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
  compileOnly("org.projectlombok:lombok")
  compileOnly("org.hibernate.orm:hibernate-jpamodelgen:6.2.6.Final")
  runtimeOnly("io.micrometer:micrometer-registry-prometheus")
  annotationProcessor("org.projectlombok:lombok")
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
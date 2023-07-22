plugins{
  java
  id("org.springframework.boot") version "3.1.0"
  id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java{
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories { 
  mavenCentral()
}

extra["springCloudVersion"] = "2022.0.3"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  //implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")  
  //implementation("org.springframework.boot:spring-boot-docker-compose:3.1.1")  
  //implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("com.mysql:mysql-connector-j:8.0.33")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0") 
  //developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test") 
  compileOnly("org.projectlombok:lombok:1.18.28") 
  annotationProcessor("org.projectlombok:lombok:1.18.28")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.solncev"
version = "1.0-SNAPSHOT"

val springVersion: String by project
val springSecurityVersion: String by project
val jakartaVersion: String by project
val hibernateVersion: String by project
val postgresVersion: String by project

repositories {
    mavenCentral()
}

application {
    mainClass = "com.solncev.Main"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("jakarta.servlet:jakarta.servlet-api:$jakartaVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("javax.mail:javax.mail-api:1.6.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("org.springframework.security:spring-security-taglibs:$springSecurityVersion")
    implementation("org.apache.tomcat:tomcat-jsp-api:10.1.20")
    implementation("javax.servlet.jsp:jsp-api:2.1")
}

tasks.test {
    useJUnitPlatform()
}
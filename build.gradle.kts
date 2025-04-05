plugins {
    id("java")
    id("application")
    id("war")
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
    implementation("org.springframework:spring-webmvc:$springVersion")
    implementation("org.springframework:spring-jdbc:$springVersion")
    implementation("org.springframework:spring-orm:$springVersion")
    implementation("org.springframework:spring-context-support:$springVersion")
    implementation("org.springframework.security:spring-security-core:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-web:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-config:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-taglibs:$springSecurityVersion")
    implementation("jakarta.servlet:jakarta.servlet-api:$jakartaVersion")
    implementation("org.hibernate:hibernate-core:$hibernateVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("org.springframework.data:spring-data-jpa:3.4.4")
    implementation("com.mchange:c3p0:0.10.2")
    implementation("org.freemarker:freemarker:2.3.34")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("org.thymeleaf:thymeleaf-spring6:3.1.3.RELEASE")
    compileOnly("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}
plugins {
    id("java")
    id("org.gretty") version "4.1.1"
    id("io.freefair.lombok") version "8.6"
}

group = "com.max"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.8.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
    implementation("org.postgresql:postgresql:42.7.7")
    implementation("javax.servlet:jstl:1.2")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:2.0.0")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:2.0.0")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}

gretty {
    httpPort = 8086
    contextPath = "/Avia"
}
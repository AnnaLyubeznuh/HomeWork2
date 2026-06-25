plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.10.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

tasks.test {
    useTestNG()
}
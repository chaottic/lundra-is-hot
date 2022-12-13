import org.lwjgl.Lwjgl.Module.*;

plugins {
    id("java")
    id("org.lwjgl.plugin") version "0.0.20"
}

group = "com.chaottic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    org.lwjgl.Lwjgl { implementation(glfw, opengl) }

    implementation("org.joml:joml:1.10.5")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
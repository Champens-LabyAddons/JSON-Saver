plugins {
    kotlin("jvm") version "1.9.21"
}

group = "dk.fvtrademarket.tooling.json"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.google.code.gson:gson:2.10")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
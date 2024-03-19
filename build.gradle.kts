plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
}

group = "dk.fvtrademarket"
version = "1.0.0"

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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dk.fvtrademarket"
            artifactId = "JSON-Saver"
            version = "1.0.0"

            from(components["java"])
        }
    }
}
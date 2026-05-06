plugins {
    kotlin("jvm") version "2.1.21"
}

group = "io.github.numq"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.arrow.core)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
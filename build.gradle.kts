plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.allopen") version "2.0.21"
    id("io.quarkus")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation(enforcedPlatform("$quarkusPlatformGroupId:quarkus-amazon-services-bom:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("com.fasterxml.uuid:java-uuid-generator:5.1.0")
    implementation("io.quarkus:quarkus-rest-jackson")
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-rest")

    implementation("io.quarkiverse.amazonservices:quarkus-amazon-dynamodb-enhanced")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "com.abbosidev"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
        javaParameters = true
    }
}

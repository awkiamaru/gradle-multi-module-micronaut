import io.micronaut.gradle.MicronautRuntime.NETTY
import io.micronaut.gradle.MicronautTestRuntime.KOTEST_5

val javaVersion: String by project

plugins {
    alias(tools.plugins.kotlin.jvm)
    alias(tools.plugins.kotlin.ksp)
    alias(tools.plugins.kotlin.allopen)
    alias(tools.plugins.shadow)

    alias(libraries.plugins.micronaut)
    alias(libraries.plugins.micronaut.aot)
}

version = "1.0.0"
group = "br.com.kullki"

repositories {
    mavenCentral()
}

dependencies {
    ksp(libraries.micronaut.http.validation)
    ksp(libraries.micronaut.serde.processor)

    implementation(libraries.micronaut.kotlin.runtime)
    implementation(libraries.micronaut.serde.jackson)

//  Database and migrations
    implementation(libraries.micronaut.data.jdbc)
    implementation(libraries.micronaut.jdbc.hikari)
    implementation(libraries.micronaut.liquibase)
    implementation(libraries.slf4j.jul.to.slf4j)

//  Kotlin tools
    implementation(tools.kotlin.reflect)
    implementation(tools.kotlin.stdlib)

    runtimeOnly(libraries.jackson.module.kotlin)
    runtimeOnly(libraries.logback.classic)
    compileOnly(libraries.micronaut.http.client)
    runtimeOnly(libraries.mysql.connector.java)
    runtimeOnly(libraries.snakeyaml)

    testImplementation(libraries.micronaut.http.client)
    testImplementation("org.testcontainers:testcontainers")
    testImplementation(libraries.testcontainers.mysql)
}


application {
    mainClass.set("br.com.kullki.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)

    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
}

graalvmNative {
    toolchainDetection = false
}

micronaut {
    runtime(NETTY)
    testRuntime(KOTEST_5)
    processing {
        incremental(true)
        annotations("br.com.kullki.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}

tasks {
    test {
        jvmArgs = listOf("-Duser.timezone=UTC", "-Djava.net.preferIPv4Stack=true")
    }
}


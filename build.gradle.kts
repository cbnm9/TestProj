import com.moowork.gradle.node.npm.NpmTask

plugins {
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
    id("com.moowork.node") version "1.3.1"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

group = "com.example.test"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.springfox", "springfox-swagger2", "2.9.2")
    implementation("io.springfox", "springfox-swagger-ui", "2.9.2")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.powermock:powermock-module-junit4:2.0.4")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.4")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

val buildNode by tasks.registering(NpmTask::class) {
    setArgs(listOf("run", "build"))
    setExecOverrides(closureOf<ExecSpec> {
        setWorkingDir("./frontend")
    })
}
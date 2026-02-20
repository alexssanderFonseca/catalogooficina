plugins {
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
	java
	jacoco
}

allprojects {
	group = "br.com.oficina"
	version = "0.0.1-SNAPSHOT"
    extra["springCloudVersion"] = "2025.1.0"

    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco")

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

	repositories {
		mavenCentral()
	}

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.1")
        implementation("jakarta.inject:jakarta.inject-api:2.0.1")
        implementation("org.springframework.boot:spring-boot-starter-opentelemetry")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
        implementation("org.springframework.boot:spring-boot-starter-flyway")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.flywaydb:flyway-core")
        implementation("org.flywaydb:flyway-database-postgresql")
        testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-parameters")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
        afterEvaluate {
            classDirectories.setFrom(files(classDirectories.files.map {
                fileTree(it).matching {
                    exclude(
                        "**/adapter/**",
                        "**/common/**",
                        "**/core/entity/**",
                        "**/core/exception/**",
                        "**/core/usecase/input/**",
                        "**/core/usecase/output/**",
                        "**/*Application*",
                        "**/*Config*",
                        "**/*DTO*"
                    )
                }
            }))
        }
    }
}

description = "Demo project for Spring Boot"

dependencies {
    implementation(project(":peca_insumo"))
    implementation(project(":servico"))
    implementation("com.h2database:h2")
    implementation("org.postgresql:postgresql:42.7.3")
}

jacoco {
    toolVersion = "0.8.12"
}

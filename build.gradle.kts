buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(Dependencies.GradlePlugins.kotlin)
        classpath(Dependencies.GradlePlugins.android)
        classpath(Dependencies.GradlePlugins.hilt)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("com.github.ben-manes.versions") version "0.39.0"
}

tasks.register("clean").configure {
    delete("build")
}

detekt {
    config = files("$projectDir/config/detekt.yml")
    source = files(projectDir)
    buildUponDefaultConfig = true
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
    include("**/*.kt")
    include("**/*.kts")
    exclude("resources/")
    exclude("build/")
}

plugins {
    id("com.android.application")
    id("common-ui-module-plugin")
}

android {
    defaultConfig {
        applicationId = Versions.APPLICATION_ID
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
        }
    }

    packagingOptions {
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/AL2.0")
    }
}

dependencies {
    implementation(project(":core-type"))
    implementation(project(":core-data"))

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.activity)
    implementation(Dependencies.AndroidX.viewmodel)

    implementation(Dependencies.coil)
}

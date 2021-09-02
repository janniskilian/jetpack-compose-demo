plugins {
    id("com.android.library")
    id("common-module-plugin")
}

kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":core-type"))

    implementation(Dependencies.AndroidX.datastorePref)
    implementation(Dependencies.AndroidX.paging)

    implementation(Dependencies.Retrofit.lib)
    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.Retrofit.loggingInterceptor)
}

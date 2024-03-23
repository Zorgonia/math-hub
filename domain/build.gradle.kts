plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kspPlugin)
}

android {
    namespace = "com.kyang.mathhub.domain"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

    }

    kotlin {
        jvmToolchain(libs.versions.jvm.toolchain.get().toInt())
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.hilt.android)
    implementation(project(":data"))
    ksp(libs.hilt.compiler)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
}

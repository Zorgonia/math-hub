plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kspPlugin)
}

android {
    namespace = "com.kyang.mathhub.feature.history"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

    }

    kotlin {
        jvmToolchain(libs.versions.jvm.toolchain.get().toInt())
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.kotlinCompilerExtensionVersion.get()
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.viewmodel.base)
    implementation(libs.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":theme"))
    implementation(project(":domain"))
    implementation(project(":helper"))
    ksp(libs.lifecycle.kapt)

    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

}

import extensions.androidTestImplementations
import extensions.implementations
import extensions.kapts
import extensions.testImplementations

plugins {
    id(Dependencies.AppPlugins.androidLibrary)
    id(Dependencies.AppPlugins.jetbrainsKotlinAndroid)
}

android {
    compileSdk = Configurations.compileSdk

    defaultConfig {
        minSdk = Configurations.minSdk
        targetSdk = Configurations.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementations(Dependencies.zoogleDependencies)
    testImplementations(Dependencies.testDependencies)
    androidTestImplementations(Dependencies.androidTestDependencies)
}
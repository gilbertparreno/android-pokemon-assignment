import extensions.androidTestImplementations
import extensions.apis
import extensions.kapts
import extensions.testImplementations

plugins {
    id(Dependencies.AppPlugins.androidLibrary)
    id(Dependencies.AppPlugins.jetbrainsKotlinAndroid)
    id(Dependencies.AppPlugins.kotlinKapt)
    id(Dependencies.AppPlugins.hiltAndroid)
}

android {
    compileSdk = Configurations.compileSdk

    defaultConfig {
        buildConfigField(
            "String",
            "POKEMON_API_URI",
            "\"https://pokeapi.co/api/v2/\""
        )
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
    apis(Dependencies.networkingDependencies)
    kapts(Dependencies.networkingKaptDependencies)
    testImplementations(Dependencies.testDependencies)
    androidTestImplementations(Dependencies.androidTestDependencies)
}

kapt {
    correctErrorTypes = true
}
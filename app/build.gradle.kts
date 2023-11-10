import com.arangoa.buildsrc.AppDependencies
import com.arangoa.buildsrc.Hilt
import com.arangoa.buildsrc.androidTestImplementationOwn
import com.arangoa.buildsrc.implementationOwn
import com.arangoa.buildsrc.testImplementationOwn

plugins {
    id ("com.android.application")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    kotlin("android")
}

android {
    namespace = "com.arangoa.logogenia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arangoa.logogenia"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data")))

    //app libs
    implementationOwn(AppDependencies.appLibraries)
    implementationOwn(AppDependencies.coroutinesLibraries)
    implementationOwn(AppDependencies.retrofitLibraries)
    implementationOwn(AppDependencies.hiltLibraries)
    implementationOwn(AppDependencies.exoPlayerLibraries)
    //test libs
    testImplementationOwn(AppDependencies.testLibraries)
    androidTestImplementationOwn(AppDependencies.androidTestLibraries)
    implementation("com.squareup:javapoet:1.13.0")
    kapt(com.arangoa.buildsrc.Hilt.compiler)

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))

    // Add the dependency for the Analytics library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-analytics")

}
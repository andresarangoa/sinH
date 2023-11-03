import com.example.buildsrc.AppDependencies
import com.example.buildsrc.Hilt
import com.example.buildsrc.androidTestImplementationOwn
import com.example.buildsrc.implementationOwn
import com.example.buildsrc.testImplementationOwn

plugins {

    id("com.google.gms.google-services")
    id ("com.android.application")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    namespace = "com.example.logogenia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.logogenia"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
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
        kotlinCompilerExtensionVersion = "1.4.3"
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

    implementation(platform("com.google.firebase:firebase-bom:32.4.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    //app libs
    implementationOwn(AppDependencies.appLibraries)
    implementationOwn(AppDependencies.coroutinesLibraries)
    implementationOwn(AppDependencies.retrofitLibraries)
    implementationOwn(AppDependencies.hiltLibraries)
    implementationOwn(AppDependencies.exoPlayerLibraries)
<<<<<<< Updated upstream
=======



    //TensorFlow
    implementationOwn(AppDependencies.tensorFlow)

    //ExoCamera
    implementationOwn(AppDependencies.exoCameraLibraries)

>>>>>>> Stashed changes
    //test libs
    testImplementationOwn(AppDependencies.testLibraries)
    androidTestImplementationOwn(AppDependencies.androidTestLibraries)
    implementation("com.squareup:javapoet:1.13.0")
    kapt(Hilt.compiler)
}
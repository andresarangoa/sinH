import com.example.buildsrc.implementationOwn
import com.example.buildsrc.testImplementationOwn
import com.example.buildsrc.AppDependencies
import com.example.buildsrc.Room

plugins {
    kotlin("android")
    id ("kotlin-kapt")
    id("com.android.library")
}


android {
    namespace = "com.example.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {

    implementationOwn(AppDependencies.coroutinesLibraries)
    implementationOwn(AppDependencies.retrofitLibraries)
    implementationOwn(AppDependencies.hiltLibraries)
    implementation("com.squareup:javapoet:1.13.0")
    implementationOwn(AppDependencies.exoPlayerLibraries)
    testImplementationOwn(AppDependencies.testLibraries)
    //room
    api(Room.roomLib)
    implementation(Room.roomKtx)
    kapt(Room.roomCompiler)
}
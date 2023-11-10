import com.arangoa.buildsrc.implementationOwn
import com.arangoa.buildsrc.testImplementationOwn
import com.arangoa.buildsrc.AppDependencies
import com.arangoa.buildsrc.Room

plugins {
    kotlin("android")
    id ("kotlin-kapt")
    id("com.android.library")
}


android {
    namespace = "com.arango.domain"
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

    implementationOwn(com.arangoa.buildsrc.AppDependencies.coroutinesLibraries)
    implementationOwn(com.arangoa.buildsrc.AppDependencies.retrofitLibraries)
    implementationOwn(com.arangoa.buildsrc.AppDependencies.hiltLibraries)
    implementation("com.squareup:javapoet:1.13.0")
    implementationOwn(com.arangoa.buildsrc.AppDependencies.exoPlayerLibraries)
    testImplementationOwn(com.arangoa.buildsrc.AppDependencies.testLibraries)
    //room
    api(com.arangoa.buildsrc.Room.roomLib)
    implementation(com.arangoa.buildsrc.Room.roomKtx)
    kapt(com.arangoa.buildsrc.Room.roomCompiler)
}
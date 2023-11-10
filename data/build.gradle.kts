import com.arangoa.buildsrc.implementationOwn
import com.arangoa.buildsrc.AppDependencies

plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    namespace = "com.arangoa.data"
    compileSdk = 34

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

    implementation(project(mapOf("path" to ":domain")))
    implementationOwn(com.arangoa.buildsrc.AppDependencies.coroutinesLibraries)
    implementationOwn(com.arangoa.buildsrc.AppDependencies.retrofitLibraries)
    implementationOwn(com.arangoa.buildsrc.AppDependencies.hiltLibraries)
}
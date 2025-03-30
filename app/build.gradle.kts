import com.example.buildsrc.AppDependencies
import com.example.buildsrc.Hilt
import com.example.buildsrc.Versions
import com.example.buildsrc.androidTestImplementationOwn
import com.example.buildsrc.implementationOwn
import com.example.buildsrc.testImplementationOwn

plugins {
    id("com.android.application")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    kotlin("android")
    // Apply the Compose compiler plugin
    id("org.jetbrains.kotlin.plugin.compose")
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
        renderscriptTargetApi = 23
        renderscriptSupportModeEnabled = false
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
        mlModelBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Match with Kotlin 2.1.20
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Add JVM arguments to fix the IllegalAccessError with KAPT
kapt {
    javacOptions {
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED")
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xjvm-default=all",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_metrics",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_reports"
        )
    }
}

dependencies {
    // Standard dependencies
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data")))

    // Add compose BOM
    implementation(platform("androidx.compose:compose-bom:2025.03.01"))

    // App libraries
    implementationOwn(AppDependencies.appLibraries)
    implementationOwn(AppDependencies.coroutinesLibraries)
    implementationOwn(AppDependencies.retrofitLibraries)
    implementationOwn(AppDependencies.hiltLibraries)
    implementationOwn(AppDependencies.exoPlayerLibraries)

    //TensorFlow
    implementationOwn(AppDependencies.tensorFlow)

    //ExoCamera
    implementationOwn(AppDependencies.exoCameraLibraries)

    //test libs
    testImplementationOwn(AppDependencies.testLibraries)
    androidTestImplementationOwn(AppDependencies.androidTestLibraries)

    kapt(Hilt.compiler)

    // Fix for dependencies with Kotlin 2.x
    implementation("androidx.collection:collection-ktx:1.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.8.0")
}
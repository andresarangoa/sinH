package com.example.buildsrc

object Versions {
    const val compose_ui = "1.6.4"
    const val coilCompose = "2.6.0"
    const val activityCompose = "1.8.2"
    const val runtimeKt = "2.7.0"
    const val coreKtx = "1.12.0"
    const val versionCompiler = "1.9.10"

    const val jUnit = "4.13.2"
    const val mockk = "1.13.10"
    const val robolectric = "4.11.1"
    const val kluent = "1.73"
    const val junitKtx = "1.1.5"
    const val mockitoCore = "5.10.0"
    const val mockitoKotlin = "5.2.1"
    const val coroutinesTest = "1.8.1"
    const val mockitoInline = "5.2.0"
    const val archCoreTest = "2.2.0"

    const val androidxTest = "1.1.5"
    const val androidxTestEspresso = "3.5.1"

    const val androidxTest = "1.1.3"
    const val androidxTestEspresso = "3.4.0"
    const val hiltVersion = "1.0.0"
    const val navigationVersion = "2.4.2"
    const val material3Version = "1.0.0-alpha13"
    const val navigationAnimation = "0.26.5-rc"
    const val exoPlayer = "1.0.0-rc01"
    const val exoCamera = "1.2.0-alpha03"
    const val tensorFlowLite = "0.3.0"
    const val tensorFlowLiteMetadata = "0.1.0"
    const val accompanistPersmission = "0.24.3-alpha"
    const val javapoet = "1.13.0"
    const val hiltVersion = "1.2.0"
    const val navigationVersion = "2.7.7"
    const val material3Version = "1.2.1"
    const val navigationAnimation = "0.32.0"
    const val exoPlayer = "1.2.1"
}

object DebugDependencies {
    private const val kanaryVersion = "2.13"
    const val leakKanary = "com.squareup.leakcanary:leakcanary-android:$kanaryVersion"
}

object Kotlin {
    const val version = "2.1.2"

    object Coroutines {
        private const val version = "1.8.1"
        private const val coreVersion = "1.8.1"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coreVersion"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coreVersion"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coreVersion"
        const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coreVersion"
    }
}

object Retrofit {
    private const val retrofitVersion = "2.10.0"
    private const val okHttpLoggingInterceptorVersion = "4.12.0"

    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpLoggingInterceptorVersion"
}

object Firebase {
    const val firebaseAuthVersion = "22.3.1"
    const val firebaseCommonVersion = "20.4.2"
    const val firebaseBoomVersion = "32.7.4"
    const val serviceAuthVersion = "21.0.0"

    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    const val firebaseCommon = "com.google.firebase:firebase-common-ktx:$firebaseCommonVersion"
    const val serviceAuth = "com.google.android.gms:play-services-auth:$serviceAuthVersion"
    const val firestore = "com.google.firebase:firebase-firestore-ktx"
    const val firebaseBoom = "com.google.firebase:firebase-bom:$firebaseBoomVersion"
    const val firebaseAuthUI = "com.firebaseui:firebase-ui-auth:9.0.0"
}

object Hilt {
    private const val version = "2.51"

    const val android = "com.google.dagger:hilt-android:$version"
    const val compiler = "com.google.dagger:hilt-compiler:$version"
    const val testing = "com.google.dagger:hilt-android-testing:$version"
}

object Room {
    private const val roomVersion = "2.6.1"

    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomLib = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
}


object AppDependencies {
    //android ui
    private const val androidXCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val androidxLifeCycle =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKt}"
    private const val androidxActivity =
        "androidx.activity:activity-compose:${Versions.activityCompose}"
    private const val androidxCompose = "androidx.compose.ui:ui:${Versions.compose_ui}"
    private const val androidxComposeUI =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose_ui}"
    private const val composeRuntime =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose_ui}"
    private const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    private const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltVersion}"
    private const val navigation =
        "androidx.navigation:navigation-compose:${Versions.navigationVersion}"
    private const val material = "androidx.compose.material3:material3:${Versions.material3Version}"
    private const val materialWindowSize =
        "androidx.compose.material3:material3-window-size-class:${Versions.material3Version}"
    private const val navigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.navigationAnimation}"
        private const val permissionsAccompanist =
        "com.google.accompanist:accompanist-permissions:${Versions.accompanistPersmission}"
        private const val javaPoet =
        "com.squareup:javapoet:${Versions.javapoet}"

    // CameraX
    private const val exoCameraCore = "androidx.camera:camera-core:${Versions.exoCamera}"
    private const val exoCamera2 = "androidx.camera:camera-camera2:${Versions.exoCamera}"
    private const val exoCameraLifecycle = "androidx.camera:camera-lifecycle:${Versions.exoCamera}"
    private const val exoCameraView = "androidx.camera:camera-view:${Versions.exoCamera}"

    // TensorFlow
    private const val tensorFlowLite = "org.tensorflow:tensorflow-lite-support:${Versions.tensorFlowLite}"
    private const val tensorFlowLiteMetadata = "androidx.camera:camera-view:${Versions.tensorFlowLiteMetadata}"


    //ExoPlayer

    private const val mediaExoPlayer =
        "androidx.media3:media3-exoplayer:${Versions.exoPlayer}"
    private const val exoPlayerDash =
        "androidx.media3:media3-exoplayer-dash:${Versions.exoPlayer}"
    private const val exoPlayerMedia =
        "androidx.media3:media3-ui:${Versions.exoPlayer}"

    //test libs
    //testImplementation
    private const val jUnit = "junit:junit:${Versions.jUnit}"
    private const val mockk = "io.mockk:mockk:${Versions.mockk}"
    private const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    private const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"

    private const val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
    private const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    private const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    private const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    private const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    private const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"

    //androidTestImplementation
    private const val androidxTest = "androidx.test.ext:junit:${Versions.androidxTest}"
    private const val androidxTestEspresso =
        "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"
    private const val androidxComposeUITest =
        "androidx.compose.ui:ui-test-junit4:${Versions.compose_ui}"

    //debug
    //debugImplementation
    private const val androidComposeUITooling =
        "androidx.compose.ui:ui-tooling:${Versions.compose_ui}"
    private const val androidComposeUiTestManifest =
        "androidx.compose.ui:ui-test-manifest:${Versions.compose_ui}"
    val appLibraries = arrayListOf<String>().apply {
        add(androidXCore)
        add(androidxLifeCycle)
        add(androidxActivity)
        add(androidxCompose)
        add(androidxComposeUI)
        add(composeRuntime)
        add(hiltNavigationCompose)
        add(navigation)
        add(material)
        add(materialWindowSize)
        add(coilCompose)
        add(navigationAnimation)
        add(permissionsAccompanist)
        add(javaPoet)
    }

    val coroutinesLibraries = arrayListOf<String>().apply {
        add(Kotlin.Coroutines.android)
        add(Kotlin.Coroutines.playServices)
        add(Kotlin.Coroutines.core)
    }

    val hiltLibraries = arrayListOf<String>().apply {
        add(Hilt.android)
    }

    val exoPlayerLibraries = arrayListOf<String>().apply {
        add(AppDependencies.exoPlayerDash)
        add(AppDependencies.exoPlayerMedia)
        add(AppDependencies.mediaExoPlayer)
    }

    val tensorFlow = arrayListOf<String>().apply {
        add(AppDependencies.tensorFlowLite)
        add(AppDependencies.tensorFlowLiteMetadata)
    }
    val exoCameraLibraries = arrayListOf<String>().apply {
        add( exoCameraCore)
        add( exoCamera2)
        add( exoCameraLifecycle)
        add( exoCameraView)
    }

    val retrofitLibraries = arrayListOf<String>().apply {
        add(Retrofit.retrofit)
        add(Retrofit.retrofitGson)
        add(Retrofit.okHttpLoggingInterceptor)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(Hilt.compiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidxTest)
        add(androidxTestEspresso)
        add(Kotlin.Coroutines.test)
        add(Hilt.testing)
    }

    val debugLibraries = arrayListOf<String>().apply {
        add(androidComposeUITooling)
        add(androidComposeUiTestManifest)
        add(DebugDependencies.leakKanary)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(jUnit)
        add(mockk)
        add(robolectric)
        add(kluent)
        add(junitKtx)
        add(mockitoCore)
        add(mockitoKotlin)
        add(coroutinesTest)
        add(mockitoInline)
        add(archCoreTest)
    }
}
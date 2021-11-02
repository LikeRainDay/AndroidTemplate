import app.web.finecloud.buildsrc.Depends
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.androidCompileSdkVersion)

    defaultConfig {
        multiDexEnabled = true
        applicationId = "app.web.finecloud.mvvmtemplate"
        minSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.minSdkVersion)
        targetSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.targetSdkVersion)
        versionCode = app.web.finecloud.buildsrc.Depends.Versions.appVersionCode
        versionName = app.web.finecloud.buildsrc.Depends.generateVersionName()
        testInstrumentationRunner =
            app.web.finecloud.buildsrc.Depends.Versions.testInstrumentationRunner
        javaCompileOptions.annotationProcessorOptions.arguments += mapOf(
            "room.schemaLocation" to "$projectDir/schemas"
        )
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            signingConfig = signingConfigs.getByName("debug")
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    lintOptions {
        isAbortOnError = false
    }
    //testOptions.unitTests.returnDefaultValues = true
    packagingOptions {
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/proguard/androidx-annotations.pro")
    }
}

tasks.withType<KotlinCompile> {
    sourceCompatibility = "unused"
    targetCompatibility = "unused"
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = freeCompilerArgs + listOf("-XXLanguage:+InlineClasses")
    }
}

dependencies {
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.kotlin)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.android_core_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.multidex)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.fragment_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_rx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.dataStore_preferences)
    //dependency injection
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.hilt_android)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.hilt_android_compiler)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.hilt_compiler)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.java_inject)
    //network
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.retrofit)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.retrofit_adapter_rx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.logging_interceptor)
    //other
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.timber)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.material)
    debugImplementation(app.web.finecloud.buildsrc.Depends.Libraries.leak_canary)
    debugImplementation(app.web.finecloud.buildsrc.Depends.Libraries.chucker)
    releaseImplementation(app.web.finecloud.buildsrc.Depends.Libraries.chucker_no_op)
    //test
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.junit)
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.test_runner)
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.espresso_core)

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
}
import app.web.finecloud.buildsrc.Depends

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {

    compileSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.androidCompileSdkVersion)

    defaultConfig {
        multiDexEnabled = true
        minSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.minSdkVersion)
        targetSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.targetSdkVersion)
        testInstrumentationRunner =
            app.web.finecloud.buildsrc.Depends.Versions.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = freeCompilerArgs + listOf("-XXLanguage:+InlineClasses")
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") { }
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

}

val unitTestImplementation by configurations.creating
configurations["compileOnly"].extendsFrom(unitTestImplementation)
configurations["testImplementation"].extendsFrom(unitTestImplementation)

dependencies {
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.kotlin)
    //android
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_rx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lifecycle_livedata)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.multidex)
    //reactive
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.rx_kotlin)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.rx_java)
    //dependency injection
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.java_inject)
    //test
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.junit)
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.mockito_core)
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.mockito_inline)
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.mockito_kotlin)
}
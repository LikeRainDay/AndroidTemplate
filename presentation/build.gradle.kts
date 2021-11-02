import app.web.finecloud.buildsrc.Depends

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdkVersion(app.web.finecloud.buildsrc.Depends.Versions.androidCompileSdkVersion)

    defaultConfig {
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
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
    }

    buildFeatures {
        viewBinding = true
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

dependencies {
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.kotlin)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.kotlin_reflect)

    //dependency injection
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.hilt_android)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.hilt_android_compiler)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.hilt_compiler)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.java_inject)
    //other
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.timber)
    //android
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.appcompat)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.constraintlayout)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.material)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.navigation_fragment_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.navigation_ui_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_rx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lifecycle_livedata_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lifecycle_runtime_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lifecycle_viewmodel_runtime_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lifecycle_common_java8)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lifecycle_viewmodel_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.multidex)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.android_core_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.fragment_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.recyclerview)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.dataStore_preferences)
    //reactive
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.rx_java_android)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.rx_binding3)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.rx_kotlin)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.autodispose)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.autodispose_android)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.autodispose_android_arch)
    //ui
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.glide)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.glide_compiler)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.lottie)
    //test
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.test_runner)
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.test_rules)
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.test_core)
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.test_ext_junit)
    androidTestImplementation(app.web.finecloud.buildsrc.Depends.Libraries.espresso_core)

    implementation(project(":domain"))
}

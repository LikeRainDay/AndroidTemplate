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
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + app.web.finecloud.buildsrc.Depends.Environments.debugBaseUrl + "\""
            )
        }
        named("release") {
            isMinifyEnabled = true
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + app.web.finecloud.buildsrc.Depends.Environments.releaseBaseUrl + "\""
            )
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
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.android_core_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.paging_rx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.multidex)
    //dependency injection
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.hilt_android)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.hilt_android_compiler)
    kapt(app.web.finecloud.buildsrc.Depends.Libraries.hilt_compiler)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.java_inject)
    //parser
    api(app.web.finecloud.buildsrc.Depends.Libraries.converter_gson)
    //network
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.retrofit)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.retrofit_adapter_rx)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.logging_interceptor)
    debugImplementation(app.web.finecloud.buildsrc.Depends.Libraries.chucker)
    releaseImplementation(app.web.finecloud.buildsrc.Depends.Libraries.chucker_no_op)
    //other
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.timber)
    implementation(app.web.finecloud.buildsrc.Depends.Libraries.material)
    //test
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.junit)
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.mockito_core)
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.mockito_inline)
    testImplementation(app.web.finecloud.buildsrc.Depends.Libraries.mockito_kotlin)

    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    implementation(project(":domain"))
}
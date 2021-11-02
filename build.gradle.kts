plugins {
    id("org.sonarqube") version app.web.finecloud.buildsrc.Depends.Versions.sonarqubeVersion
    detekt
    id("com.github.ben-manes.versions") version app.web.finecloud.buildsrc.Depends.Versions.checkDependencyVersionsVersion
    id("com.osacky.doctor") version app.web.finecloud.buildsrc.Depends.Versions.gradleDoctorVersion
}

buildscript {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/public")
        google()
        mavenCentral()
    }
    dependencies {
        classpath(app.web.finecloud.buildsrc.Depends.ClassPaths.gradle)
        classpath(
            kotlin(
                app.web.finecloud.buildsrc.Depends.ClassPaths.kotlin_gradle_plugin,
                version = app.web.finecloud.buildsrc.Depends.Versions.kotlinVersion
            )
        )
        classpath(app.web.finecloud.buildsrc.Depends.ClassPaths.navigation_safe_args_gradle_plugin)
        classpath(app.web.finecloud.buildsrc.Depends.ClassPaths.hilt_android_gradle_plugin)
        classpath(app.web.finecloud.buildsrc.Depends.ClassPaths.sonarqube_gradle_plugin)
    }
}

allprojects {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/public")
        google()
        mavenCentral()
        maven("https://maven.google.com/")
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

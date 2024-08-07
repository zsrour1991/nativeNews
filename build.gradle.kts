// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
//        maven { url ="https://jitpack.io" }
    }
    dependencies {
        var nav_version = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    id("com.android.application") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
//    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false

     id ("com.android.library") version "7.3.1" apply false
}
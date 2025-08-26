// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
}

buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
//        classpath ("com.android.tools.build:gradle:4.1.1")
        classpath ("com.android.tools.build:gradle:8.11.1")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

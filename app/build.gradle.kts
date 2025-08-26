plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.app.internetspeed"
    compileSdk = 35
    buildToolsVersion = "30.0.3"

    lintOptions {
        disable ("InvalidPackage")
        var checkReleaseBuilds = false
    }

    defaultConfig {
        applicationId = "com.app.internetspeed"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation (files("libs/achartengine-1.2.0.jar"))

    implementation (libs.sdp.android)

    implementation(libs.recyclerview)
    implementation (libs.rippleswitch)

    implementation (libs.tedpermission.normal)
    implementation(libs.play.services.ads)
    implementation (libs.rate.me)

    implementation (libs.uipopupmenu)
    implementation(libs.okhttp)
    implementation(project(":roundbutton"))
    implementation (libs.work.runtime)
    implementation(project(":androidlikebutton"))
    implementation(project(":tastytoast"))

    implementation (libs.multidex)
    implementation (libs.recyclerview.v110)
    implementation (libs.material.v121)
}
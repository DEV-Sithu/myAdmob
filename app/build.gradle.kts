plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.mk.myadmob"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.mk.myadmob"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Build Variants (Dev/Prod)
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            resValue("string", "ad_app_id", "ca-app-pub-3940256099942544~3347511713") // Test App ID
            resValue("string", "ad_banner_id", "ca-app-pub-3940256099942544/6300978111") // Test Banner
        }

        create("prod") {
            dimension = "environment"
            resValue("string", "ad_app_id", "YOUR_REAL_APP_ID") //  Production ID နဲ့အစားထိုးပါ
            resValue("string", "ad_banner_id", "YOUR_REAL_BANNER_ID")
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.core:core-splashscreen:1.0.0")
    //admob
    implementation("androidx.lifecycle:lifecycle-process:2.9.0")
    implementation("com.google.android.gms:play-services-ads:22.6.0")
}
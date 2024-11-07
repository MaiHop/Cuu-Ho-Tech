plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.cuu_ho_tech"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.cuu_ho_tech"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    //shadow
    implementation(libs.xneumorphism)
    implementation(libs.xmultidex)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //lifecycle
    implementation(libs.lifecycle.extensions)

    //MapView
    implementation(libs.osmdroid.android)
    implementation(libs.play.services.location)


    //FlowLayout
    implementation(libs.flow.layout)

    //SeekBar
    implementation(libs.circularseekbar)

    implementation(libs.flexbox)

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    //Google
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    //Icon
    implementation("androidx.emoji2:emoji2-emojipicker:1.5.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
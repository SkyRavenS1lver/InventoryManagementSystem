plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")


}

android {
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
    namespace = "revandra.projects.inventorymanagementsystem"
    compileSdk = 34

    defaultConfig {
        applicationId = "revandra.projects.inventorymanagementsystem"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("com.google.truth:truth:1.0.1")
    androidTestImplementation ("android.arch.core:core-testing:1.0.0")
    androidTestImplementation ("androidx.test:core-ktx:1.5.0")
    androidTestImplementation ("com.android.support.test:rules:1.0.2")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    androidTestImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //Pie Chart
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.7.1")
}
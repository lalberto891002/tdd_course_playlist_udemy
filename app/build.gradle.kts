plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.video_solution"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.video_solution"
        minSdk = 24
        targetSdk = 34
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //ktx android
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation ("org.mockito:mockito-inline:2.21.0")
    testImplementation ("org.mockito:mockito-core:3.10.0")
    androidTestImplementation ("org.mockito:mockito-android:3.10.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    testImplementation ("androidx.arch.core:core-testing:2.2.0")

    androidTestImplementation ("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.adevinta.android:barista:4.2.0") {
        exclude("org.jetbrains.kotlin") // Only if you already use Kotlin in your project
    }

    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("org.hamcrest:hamcrest-library:2.2")

}
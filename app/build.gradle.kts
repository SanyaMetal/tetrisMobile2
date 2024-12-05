plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

}

android {
    namespace = "com.example.tetrismobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tetrismobile"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    buildFeatures {
        compose = true
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }


    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/scala", "src/main/java")
            res.srcDir("src/main/res")
            resources.srcDir("src/main/res")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material:material:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Для Compose-темизации
    implementation("androidx.compose.material3:material3:1.1.2")

    // Для тестирования Compose (если нужно)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")

    implementation(project(":scala-core"))
    implementation("org.scala-lang:scala-library:2.13.15") // Убедитесь, что эта библиотека включена

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.5")

}

//tasks.withType<org.gradle.api.tasks.compile.JavaCompile>().configureEach {
//    dependsOn("compileScala")
//}
//
//tasks.named<org.gradle.api.tasks.scala.ScalaCompile>("compileScala") {
//    source = fileTree("src/main/scala")
//    classpath = sourceSets["main"].compileClasspath
//    destinationDirectory.set(file("build/intermediates/javac/debug/classes"))
//}

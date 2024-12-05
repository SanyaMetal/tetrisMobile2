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

    buildFeatures {
        compose = true
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
    implementation(project(":scala-core"))
    implementation("org.scala-lang:scala-library:2.13.15") // Убедитесь, что эта библиотека включена
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

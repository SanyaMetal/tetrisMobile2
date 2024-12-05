plugins {
    id("com.android.library")  // Плагин для Android-библиотеки
}

android {
    namespace = "com.example.tetrismobile"  // Пространство имен
    compileSdk = 34  // Версия SDK

    defaultConfig {
        minSdk = 26  // Минимальная версия SDK
        targetSdk = 34  // Целевая версия SDK
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/scala", "src/main/java")  // Исходники Scala и Java
            res.srcDir("src/main/res")  // Каталог с ресурсами
            resources.srcDir("src/main/res")  // Ресурсы проекта
        }
    }

    packagingOptions {
        exclude("META-INF/LICENSE-notice.md")
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/NOTICE.md")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/*.kotlin_module")
    }
}

repositories {
    mavenCentral()  // Используем центральный репозиторий Maven
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.15")  // Библиотека Scala
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        androidTestImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("junit:junit:4.13.2")


}

// Регистрация задачи для компиляции Scala
tasks.register<JavaCompile>("compileScala") {
    source = fileTree("src/main/scala")  // Исходники Scala
    classpath = sourceSets["main"].compileClasspath  // Класспат для компиляции
    destinationDirectory.set(file("build/intermediates/javac/debug/classes"))  // Папка для скомпилированных классов
}

tasks.withType<org.gradle.api.tasks.compile.JavaCompile>().configureEach {
    options.encoding = "UTF-8"  // Устанавливаем кодировку
}

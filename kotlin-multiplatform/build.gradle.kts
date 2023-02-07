import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("maven-publish")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "KmmVerTest"
            xcf.add(this)
        }
    }
    js(IR) {
        moduleName = "kmm-ver-test"
        nodejs()
        binaries.library()
    }

    cocoapods {
        summary = "Some description for the KmmVerTest Module"
        homepage = "https://github.com/osakila/KmmVerTest"
        name = "KmmVerTest"
        authors = "LASSIC"
        license = "MIT"
        version = "1.0"
        source = "{ :http => 'https://github.com/osakila/KmmVerTest.git'}"
        ios.deploymentTarget = "14.0"
        framework {
            baseName = "KmmVerTest"
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.kmmvertest"
    compileSdk = 33
    defaultConfig {
        minSdk = 25
        targetSdk = 33
        setProperty("archivesBaseName", "kmm-ver-test")
    }
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"])
                groupId = "lassic.co.jp"
                artifactId = "kmm-ver-test"
                version = "1.0"
            }
            // Creates a Maven publication called “debug”.
            create<MavenPublication>("debug") {
                // Applies the component for the debug build variant.
                from(components["debug"])
                groupId = "lassic.co.jp"
                artifactId = "kmm-ver-test-debug"
                version = "1.0"
            }
        }
        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/ricohapi/theta-client")
                credentials {
                    username = System.getenv("GITHUB_USER")
                    password = System.getenv("GITHUB_PAT") // Personal access token
                }
            }
        }
    }
}

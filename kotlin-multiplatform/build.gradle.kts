import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import java.util.*

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.dokka")
    kotlin("native.cocoapods")
    signing
}

// Init publish property
initProp()

val lib_version = "1.0.1"

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

    cocoapods {
        summary = "Some description for the KmmVerTest Module"
        homepage = "https://github.com/osakila/KmmVerTest"
        name = "KmmVerTest"
        authors = "LASSIC"
        license = "MIT"
        version = lib_version
        source = "{ :http => 'https://github.com/osakila/KmmVerTest/releases/download/${lib_version}/KmmVerTest.xcframework.zip' }"
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

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    initProp()
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"])
                artifact(javadocJar.get())
                groupId = "io.github.osakila"
                artifactId = "kmm-ver-test"
                version = lib_version
                pom {
                    name.set("kmm-ver-test")
                    description.set("kmm-ver-test ${lib_version} - Lightweight logging framework for Kotlin")
                    url.set("https://github.com/osakila/KmmVerTest")
                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            name.set("osakila")
                            email.set("osaki@lassic.co.jp")
                            organization.set("github")
                            organizationUrl.set("https://www.github.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/osakila/KmmVerTest.git")
                        developerConnection.set("scm:git:ssh://github.com:osakila/KmmVerTest.git")
                        url.set("https://github.com/osakila/KmmVerTest/tree/main")
                    }
                }
            }
        }
        repositories {
            maven {
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = getExtraString("ossrhUsername")
                    password = getExtraString("ossrhPassword")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}


ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

fun initProp() {
    val secretPropsFile = project.rootProject.file("local.properties")
    if (secretPropsFile.exists()) {
        secretPropsFile.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            ext[name.toString()] = value
        }
    } else {
        ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
        ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
        ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
        ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
        ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
    }
    System.setProperty("signing.keyId", getExtraString("signing.keyId"))
    System.setProperty("signing.password", getExtraString("signing.password"))
    System.setProperty("signing.secretKeyRingFile", getExtraString("signing.secretKeyRingFile"))
}

fun getExtraString(name: String) = ext[name]?.toString()

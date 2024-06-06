plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    id("maven-publish")
}

android {
    namespace = "com.simform.bagel"
    compileSdk = libs.versions.compileSdk.get().toInt()

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.simform"
                artifactId = "bagel"
                version = "1.0.0"

                pom {
                    packaging = "aar"
                    name.set("Bagel")
                    description.set("Bagel is a little native iOS/Android network debugger")
                    url.set("https://github.com/mobile-simformsolutions/Bagel.git")
                    inceptionYear.set("2024")

                    licenses {
                        license {
                            name.set("Apache License Version 2.0, January 2004")
                            url.set("https://github.com/mobile-simformsolutions/Bagel?tab=License-1-ov-file")
                        }
                    }

                    developers {
                        developer {
                            id.set("simform")
                            name.set("Simform")
                            email.set("developer@simform.com")
                        }
                    }

                    scm {
                        connection.set("scm:git@github.com:mobile-simformsolutions/Bagel")
                        developerConnection.set("scm:git@github.com:mobile-simformsolutions/Bagel.git")
                        url.set("https://github.com/mobile-simformsolutions/Bagel.git")
                    }
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.logging.interceptor)
    implementation(libs.timber)
    implementation(libs.socket.io.client)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
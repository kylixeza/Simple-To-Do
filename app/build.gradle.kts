plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = App.applicationId
    compileSdk = App.compileSdk

    defaultConfig {
        applicationId = App.applicationId
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create(Flavors.dev) {
            dimension = "version"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "${App.name}-dev")
        }
        create(Flavors.prod) {
            dimension = "version"
            resValue("string", "app_name", App.name)
        }
    }
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-runtime-compose-android:2.8.4")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    implOf (Libs.coreKtx)
    implOf (Libs.lifecycleKtx)
    implOf (Libs.activityCompose)

    platformImplOf (Libs.koinBom)
    platformImplOf (Libs.composeBom)

    implComposeDependencies()
    implKoinDependencies()
    implRoomDependencies()
    ksp(Libs.roomCompiler)

    implementation("com.github.commandiron:WheelPickerCompose:1.1.11")
    implementation("com.airbnb.android:lottie-compose:4.2.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
}
import org.gradle.api.artifacts.dsl.DependencyHandler

object App {
    const val applicationId = "com.kylix.algostudioseniormobiledevelopertest"
    const val name = "Simple Task"
    const val compileSdk = 35
    const val targetSdk = 34 //due to new playstore's policy
    const val minSdk = 26
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Flavors {
    const val dev = "dev"
    const val prod = "prod"
}

object Versions {
    const val kotlin = "1.9.20"
    const val agp = "8.2.0"
    const val compose = "1.5.5"

    const val coreKtx = "1.13.1"
    const val lifecycleKtx = "2.8.4"
    const val activityCompose = "1.9.1"
    const val composeBom = "2023.08.00"

    const val junit = "4.13.2"
    const val extJunit = "1.2.1"
    const val espresso = "3.6.1"

    const val koin = "3.5.6"
}

object Libs {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"

    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"

    const val koinBom = "io.insert-koin:koin-bom:${Versions.koin}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

infix fun DependencyHandler.implOf(dependency: String) {
    add("implementation", dependency)
}

infix fun DependencyHandler.platformImplOf(dependency: String) {
    val platform = platform(dependency)
    add("implementation", platform)
}

infix fun DependencyHandler.testImplOf(dependency: String) {
    add("testImplementation", dependency)
}

infix fun DependencyHandler.androidTestImplOf(dependency: String) {
    add("androidTestImplementation", dependency)
}

infix fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.implComposeDependencies() {
    add("implementation", Libs.composeUi)
    add("implementation", Libs.composeUiGraphics)
    add("implementation", Libs.composeUiToolingPreview)
    add("implementation", Libs.composeMaterial3)
}

fun DependencyHandler.implKoinDependencies() {
    add("implementation", Libs.koinCore)
    add("implementation", Libs.koinAndroid)
    add("implementation", Libs.koinCompose)
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.devtools.ksp") version "1.9.20-1.0.13"
    id("com.android.application") version Versions.agp apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
}
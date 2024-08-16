// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.devtools.ksp") version "2.0.10-1.0.24"
    id("com.android.application") version Versions.agp apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
}
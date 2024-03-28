pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableAPIUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MathHub"
include(":app")
include(":domain")
include(":theme")
include(":feature:mathquestion")
include(":feature:tip")
include(":feature:history")
include(":navigation")
include(":data")
include(":helper")

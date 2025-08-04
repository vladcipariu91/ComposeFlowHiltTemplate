pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AppTemplate"
include(":app")
include(":core:network")
include(":core:ui")
include(":feature:character_list")
include(":feature:character_list:data")
include(":feature:character_list:domain")

include(":feature:template")
include(":feature:template:domain")
include(":feature:template:data")

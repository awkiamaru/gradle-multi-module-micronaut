rootProject.name="kullki-backend"


dependencyResolutionManagement {
	val kotlinVersion: String by settings
	val kspVersion: String by settings
	val shadowVersion: String by settings
	val micronautPluginVersion: String by settings
	val micronautVersion: String by settings

	versionCatalogs {
		repositories {
			mavenCentral()
		}

		create("tools") {
			version("kotlin", kotlinVersion)
			version("ksp", kspVersion)
			version("shadow", shadowVersion)

			plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
			plugin("kotlin-allopen", "org.jetbrains.kotlin.plugin.allopen").versionRef("kotlin")
			plugin("kotlin-ksp", "com.google.devtools.ksp").versionRef("ksp")
			plugin("shadow", "com.github.johnrengelman.shadow").versionRef("shadow")

			library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
			library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").versionRef("kotlin")
		}

		create("libraries") {
			version("micronaut-plugin", micronautPluginVersion)
			plugin("micronaut", "io.micronaut.application").versionRef("micronaut-plugin")
			plugin("micronaut-aot", "io.micronaut.aot").versionRef("micronaut-plugin")

			from("io.micronaut.platform:micronaut-platform:${micronautVersion}")
		}
	}
}

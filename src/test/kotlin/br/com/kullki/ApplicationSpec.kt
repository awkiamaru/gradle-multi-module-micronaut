package br.com.kullki

import io.kotest.core.spec.style.StringSpec
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest
class ApplicationSpec(
	private val application: EmbeddedApplication<*>
): StringSpec({
	"application started successfully" {
		assert(application.isRunning)
	}
})
package br.com.kullki

import io.micronaut.runtime.Micronaut.run
import org.slf4j.bridge.SLF4JBridgeHandler.install
import org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger

fun main(args: Array<String>) {
	removeHandlersForRootLogger()
	install()

	run(*args)
}


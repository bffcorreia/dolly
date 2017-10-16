package io.github.bffcorreia.dolly

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Factory(val name: String = "")

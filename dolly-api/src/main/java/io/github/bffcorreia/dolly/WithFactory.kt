package io.github.bffcorreia.dolly

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
annotation class WithFactory(val factoryName: String)

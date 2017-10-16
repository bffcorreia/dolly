package io.github.bffcorreia.processor

import com.google.auto.service.AutoService
import io.github.bffcorreia.dolly.Factory
import io.github.bffcorreia.dolly.GeneratedId
import io.github.bffcorreia.dolly.WithFactory
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic


@AutoService(Processor::class)
class DollyProcessor : AbstractProcessor() {

  private lateinit var typeUtils: Types
  private lateinit var elementUtils: Elements
  private lateinit var filer: Filer
  private lateinit var messager: Messager

  @Synchronized override fun init(processingEnv: ProcessingEnvironment) {
    super.init(processingEnv)
    typeUtils = processingEnv.typeUtils
    elementUtils = processingEnv.elementUtils
    filer = processingEnv.filer
    messager = processingEnv.messager
  }

  override fun getSupportedAnnotationTypes(): Set<String> {
    return setOf(Factory::class.java.canonicalName, GeneratedId::class.java.canonicalName,
        WithFactory::class.java.canonicalName)
  }

  override fun getSupportedSourceVersion(): SourceVersion {
    return SourceVersion.latestSupported()
  }

  override fun process(annotations: Set<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
    val elements = roundEnv.getElementsAnnotatedWith(Factory::class.java)
    //.filterIsInstance<TypeElement>()
    //.map { buildAnnotatedClass(it) }

    return true
  }

  private fun error(e: Element, msg: String, vararg args: Any) {
    messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, *args), e)
  }
}
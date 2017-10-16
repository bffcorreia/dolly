package io.github.bffcorreia.dolly

import java.util.concurrent.atomic.AtomicInteger

abstract class Dolly<out T> {

  private val defaultListSize = 3
  private val currentId = AtomicInteger()

  internal abstract fun build(): T

  fun buildList(): List<T> {
    return buildList(defaultListSize)
  }

  fun buildList(size: Int): List<T> {
    val list = ArrayList<T>(size)
    for (i in 1..size) {
      list.add(build())
    }
    return list
  }
}

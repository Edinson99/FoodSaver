package com.foodsaver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodsaverApplication

fun main(args: Array<String>) {
    runApplication<FoodsaverApplication>(*args)
}
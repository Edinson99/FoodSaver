package com.example.foodsaver

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun string_concatenation_works() {
        val result = "Food" + "Saver"
        assertEquals("FoodSaver", result)
    }

    @Test
    fun list_operations_work() {
        val products = listOf("Tomate", "Lechuga", "Manzana")
        assertEquals(3, products.size)
        assertTrue(products.contains("Tomate"))
    }
}
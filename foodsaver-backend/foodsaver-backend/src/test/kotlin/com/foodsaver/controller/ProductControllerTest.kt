package com.foodsaver.controller

import com.foodsaver.model.Product
import com.foodsaver.model.ProductCategory
import com.foodsaver.model.ProductStatus
import com.foodsaver.model.User
import com.foodsaver.model.UserType
import com.foodsaver.service.ProductService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@WebMvcTest(ProductController::class)
class ProductControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var productService: ProductService
    
    private lateinit var product: Product
    private lateinit var vendor: User

    @BeforeEach
    fun setUp() {
        vendor = User(
            id = 1L,
            email = "vendor@example.com",
            password = "password",
            name = "Test Vendor",
            phone = "123456789",
            location = "Test Location",
            userType = UserType.VENDOR
        )
        
        product = Product(
            id = 1L,
            name = "Test Product",
            category = ProductCategory.FRUTAS,
            quantity = "1 kg",
            price = 10.0,
            description = "Test Description",
            location = "Test Location",
            photoUrl = null,
            isForSale = true,
            status = ProductStatus.AVAILABLE,
            expiryDate = LocalDateTime.now().plusDays(7),
            vendor = vendor
        )
    }

    @Test
    fun `should create a product`() {
        `when`(productService.createProduct(any())).thenReturn(product)

        mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{"name": "Test Product", "description": "Test Description", "price": 10.0}"""))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value("Test Product"))
    }

    @Test
    fun `should get a product by id`() {
        `when`(productService.getProductById(1)).thenReturn(product)

        mockMvc.perform(get("/products/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Test Product"))
    }

    @Test
    fun `should update a product`() {
        val updatedProduct = product.copy(name = "Updated Product")
        `when`(productService.updateProduct(eq(1L), any())).thenReturn(updatedProduct)

        mockMvc.perform(put("/api/products/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{"name": "Updated Product", "description": "Test Description", "price": 10.0}"""))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Updated Product"))
    }
}
package com.mercadolivro.controller.mapper

import com.mercadolivro.controller.request.PurchaseRequest
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    fun toPurchaseModel(request: PurchaseRequest): PurchaseModel {
        val customer = customerService.getById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return  PurchaseModel(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}
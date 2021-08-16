package com.mercadolivro.service

import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.UserCustomDetails
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findByIdOrNull(id.toInt()) ?:
            AuthenticationException("Usuário não encontrado", "999")
        return UserCustomDetails(customer as CustomerModel)
    }
}
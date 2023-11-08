package com.musinsa.entity.product

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity(name = "product_price")
class ProductPrice (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    val category: String,
    val brand : String,
    var price : Long,
    @CreationTimestamp
    @Column(nullable = false)
    val dateCreated : LocalDateTime,
    @UpdateTimestamp
    var dateUpdated : LocalDateTime

    )
{
}

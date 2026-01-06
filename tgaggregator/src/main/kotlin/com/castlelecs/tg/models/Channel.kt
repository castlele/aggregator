package com.castlelecs.tg.models

import jakarta.persistence.Id
import jakarta.persistence.Entity

@Entity
data class Channel(
    @Id
    val id: Long,
    val keywords: List<String>,
)

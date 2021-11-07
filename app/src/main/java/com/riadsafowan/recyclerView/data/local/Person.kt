package com.riadsafowan.recyclerView.data.local

import androidx.room.Entity

@Entity(primaryKeys = ["firstName", "lastName"])
data class Person(
    val firstName: String,
    val lastName: String
)

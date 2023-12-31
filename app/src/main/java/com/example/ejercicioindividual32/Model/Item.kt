package com.example.ejercicioindividual32.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "item_table")
data class Item (

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int = 0,
    val itemName: String,
    val itemPrice: Int,
    val itemCant: Int
        )
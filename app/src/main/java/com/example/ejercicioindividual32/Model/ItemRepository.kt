package com.example.ejercicioindividual32.Model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao){

    val listAllItem: LiveData<List<Item>> = itemDao.getAllItems()

    suspend fun insertItem(item: Item){
        itemDao.insertItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item)
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            itemDao.deleteAll()
        }
    }

}
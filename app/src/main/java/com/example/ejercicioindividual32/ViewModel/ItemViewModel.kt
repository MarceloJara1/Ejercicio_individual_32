package com.example.ejercicioindividual32.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicioindividual32.Model.Item
import com.example.ejercicioindividual32.Model.ItemDb
import com.example.ejercicioindividual32.Model.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel (application: Application): AndroidViewModel(application){

    private val repository: ItemRepository

    val allItem: LiveData<List<Item>>

    init {
        val itemDao = ItemDb.getDatabase(application).getItemDao()
        repository = ItemRepository(itemDao)
        allItem = repository.listAllItem
    }

    fun insertItem(item: Item) = viewModelScope.launch {
        repository.insertItem(item)
    }

    fun updateItem(item: Item) = viewModelScope.launch {
        repository.updateItem(item)
    }

    fun deleteAllItem() = viewModelScope.launch {
        repository.deleteAll()
    }

    private val selectedItem: MutableLiveData<Item?> = MutableLiveData()

    fun selected(item: Item?){
        selectedItem.value = item
    }

    fun selectedItem(): LiveData<Item?> = selectedItem

}
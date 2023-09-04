package com.udacity.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

//    fun addShoe(shoe: Shoe) {
//        if (_shoesList.value == null) {
//            _shoesList.value = mutableListOf(shoe)
//        } else {
//            _shoesList.value?.add(shoe)
//        }
//    }

    fun isEmpty(): Boolean {
        return _shoeList.value == null || _shoeList.value?.isEmpty() == true
    }

    fun addShoe(shoe: Shoe) {
        if (_shoeList.value == null) {
            _shoeList.value = mutableListOf(shoe)
        } else {
            _shoeList.value?.add(shoe)
        }
    }
}
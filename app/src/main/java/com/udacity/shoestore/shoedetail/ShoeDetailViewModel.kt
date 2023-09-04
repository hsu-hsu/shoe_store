package com.udacity.shoestore.shoedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel: ViewModel() {

    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDesc = MutableLiveData<String>()

    fun validateFields() : Boolean {
        return fieldNotEmpty(shoeName) &&
                fieldNotEmpty(shoeSize) &&
                fieldNotEmpty(shoeCompany) &&
                fieldNotEmpty(shoeDesc)
    }

    private fun fieldNotEmpty(data: MutableLiveData<String>) : Boolean {
        return data.value != null && data.value?.isNotEmpty() == true
    }

    private fun shoeSizeAsDouble() : Double {
        if (!fieldNotEmpty(shoeSize)) return 0.0
        return shoeSize.value?.toDouble()!!
    }

    fun buildShoe() : Shoe {
        return Shoe(
            shoeName.value ?: "",
            shoeSizeAsDouble(),
            shoeCompany.value ?: "",
            shoeDesc.value ?: "",
            arrayListOf(""))
    }

}
package com.udacity.shoestore.shoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list,
            container,false)
        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoesList ->
            for (shoe in shoesList) {
                addShoeToView(container, shoe)
            }
        })
        return binding.root
    }

    private fun addShoeToView(
        container: ViewGroup?,
        shoe: Shoe
    ) {
        val shoeBinding: ShoeItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.shoe_item, container, false
        )
        shoeBinding.shoe = shoe
        binding.shoeList.addView(shoeBinding.root)
    }
}
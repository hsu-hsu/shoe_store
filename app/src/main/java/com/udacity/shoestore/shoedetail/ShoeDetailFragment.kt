package com.udacity.shoestore.shoedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.shoelist.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var shoeDetailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail,
            container, false)
        shoeDetailViewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]
        binding.viewModel = shoeDetailViewModel
        binding.lifecycleOwner = this

        binding.btnSave.setOnClickListener{
            if (shoeDetailViewModel.validateFields()) {
                val shoe = shoeDetailViewModel.buildShoe()
                shoeViewModel.addShoe(shoe)
                goBackToShoeList()
            } else {
                Toast.makeText(context, "Please fill the information", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            goBackToShoeList()
        }
        return binding.root
    }

    private fun goBackToShoeList() {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

}
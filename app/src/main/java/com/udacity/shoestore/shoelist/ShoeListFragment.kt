package com.udacity.shoestore.shoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
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
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener{
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_shoe_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return true
    }
}
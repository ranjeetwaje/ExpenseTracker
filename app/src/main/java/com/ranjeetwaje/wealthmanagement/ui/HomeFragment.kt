package com.ranjeetwaje.wealthmanagement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.adapter.ExpenseListAdapter
import com.ranjeetwaje.wealthmanagement.database.AppDataBase
import com.ranjeetwaje.wealthmanagement.database.WealthManagementDao
import com.ranjeetwaje.wealthmanagement.database.WealthManagementEntity
import com.ranjeetwaje.wealthmanagement.databinding.FragmentHomeBinding
import com.ranjeetwaje.wealthmanagement.repository.WealthManagementRepository
import com.ranjeetwaje.wealthmanagement.viewmodel.WealthManagementViewModel
import com.ranjeetwaje.wealthmanagement.viewmodelfactory.WealthManagementViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExpenseListAdapter

    private val dao: WealthManagementDao by lazy {
        AppDataBase.getInstance(this.context).wealthManagementDao
    }

    private val repository: WealthManagementRepository by lazy {
        WealthManagementRepository(dao)
    }

    private val wealthManagementViewModel: WealthManagementViewModel by lazy {
        ViewModelProvider(this, WealthManagementViewModelFactory(repository))
            .get(WealthManagementViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.viewModel = wealthManagementViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_incomeExpenseFragment)
        }

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        adapter = ExpenseListAdapter { selectedItem : WealthManagementEntity -> listItemClicked(selectedItem)}
        binding.recyclerview.adapter = adapter
        displayKiranaList()
    }

    private fun displayKiranaList() {
        wealthManagementViewModel.expenseList.observe(this.viewLifecycleOwner, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(wealthManagementEntity: WealthManagementEntity) {
//        kiranaListViewModel.initUpdateAndDelete(kiranaListEntity)
    }

}

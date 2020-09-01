package com.ranjeetwaje.wealthmanagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.database.AppDataBase
import com.ranjeetwaje.wealthmanagement.database.CategoryDao
import com.ranjeetwaje.wealthmanagement.database.TransactionTypeDao
import com.ranjeetwaje.wealthmanagement.database.WealthManagementDao
import com.ranjeetwaje.wealthmanagement.databinding.FragmentIncomeExpenseBinding
import com.ranjeetwaje.wealthmanagement.repository.WealthManagementRepository
import com.ranjeetwaje.wealthmanagement.viewmodel.WealthManagementViewModel
import com.ranjeetwaje.wealthmanagement.viewmodelfactory.WealthManagementViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class IncomeExpenseFragment : Fragment() {

    private lateinit var binding: FragmentIncomeExpenseBinding

    private val dao: WealthManagementDao by lazy {
        AppDataBase.getInstance(this.context).wealthManagementDao
    }

    private val categoryDao: CategoryDao by lazy {
        AppDataBase.getInstance(this.context).categoryDao
    }
    private val transactionTypeDao: TransactionTypeDao by lazy {
        AppDataBase.getInstance(this.context).transactionTypeDao
    }

    private val repository: WealthManagementRepository by lazy {
        WealthManagementRepository(dao, categoryDao, transactionTypeDao)
    }

    private val wealthManagementViewModel: WealthManagementViewModel by lazy {
        ViewModelProvider(this, WealthManagementViewModelFactory(repository))
            .get(WealthManagementViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_income_expense, container, false)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        binding.viewModel = wealthManagementViewModel
        binding.lifecycleOwner = this

        wealthManagementViewModel.message.observe(this.viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })

        wealthManagementViewModel.isCreatedSuccessfully.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                binding.root.findNavController().navigate(R.id.action_incomeExpenseFragment_to_homeFragment)
            } else {

            }
        })

        wealthManagementViewModel.categoryList.observe(
            this.viewLifecycleOwner,
            Observer {
                val item = arrayOfNulls<String>(it.size)
                for(i in it.indices) item[i] = it[i].category
                val spinnerAdapter = ArrayAdapter(this.requireActivity(), android.R.layout.simple_spinner_item, item)
                binding.categorySpinner.adapter = spinnerAdapter
            }
        )

        wealthManagementViewModel.transactionTypes.observe(
            this.viewLifecycleOwner,
            Observer {
                val item = arrayOfNulls<String>(it.size)
                for(i in it.indices) item[i] = it[i].transaction_type
                val spinnerAdapter = ArrayAdapter(this.requireActivity(), android.R.layout.simple_spinner_item, item)
                binding.transactionTypeSpinner.adapter = spinnerAdapter
            }
        )

        return binding.root
    }

}

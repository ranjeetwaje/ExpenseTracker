package com.ranjeetwaje.wealthmanagement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.database.WealthManagementEntity
import com.ranjeetwaje.wealthmanagement.databinding.ListItemViewBinding

class ExpenseListAdapter(private val clickListener: (WealthManagementEntity) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {

    private val entity = ArrayList<WealthManagementEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_view, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return entity.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(entity[position], clickListener)
    }

    fun setList(wealthManagementEntity: List<WealthManagementEntity>) {
        entity.clear()
        entity.addAll(wealthManagementEntity)
    }

}

class MyViewHolder(var binding: ListItemViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(wealthManagementEntity: WealthManagementEntity, clickListener: (WealthManagementEntity) -> Unit) {
        binding.nameTextView.text = wealthManagementEntity.expenseType + " on " + wealthManagementEntity.expenseDate
        binding.spendingTextView.text = "Rs. " + wealthManagementEntity.expenseAmount
        binding.placeTextView.text = "Place: "+ wealthManagementEntity.expensePlace

        binding.listItemLayout.setOnClickListener {
            clickListener(wealthManagementEntity)
        }
    }
}
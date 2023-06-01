package com.zeller.terminalapp.transactionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeller.terminalapp.R
import com.zeller.terminalapp.databinding.TransactionItemBinding
import com.zeller.terminalapp.transactions.Transactions

class TransactionRecyclerAdapter(private val transactions: List<Transactions>) :
    RecyclerView.Adapter<TransactionRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val balance: TextView = itemView.findViewById(R.id.balance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransactionItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = transactions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        val amount = transaction.amount.toString()
        holder.balance.text = if (transaction.isDeposit) "+$amount" else "-$amount"
    }
}
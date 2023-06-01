package com.zeller.terminalapp.transactionList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeller.terminalapp.MainViewModel
import com.zeller.terminalapp.databinding.ActivityTransactionsListViewBinding

class TransactionsListView : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionsListViewBinding
    private lateinit var transactionAdapter: TransactionRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        transactionAdapter =
            TransactionRecyclerAdapter(MainViewModel.transactions.getTransactions())
        binding.transactionList.layoutManager = LinearLayoutManager(this)
        binding.transactionList.adapter = transactionAdapter
    }
}
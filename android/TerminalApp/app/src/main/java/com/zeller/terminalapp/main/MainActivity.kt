package com.zeller.terminalapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zeller.terminalapp.MainViewModel
import com.zeller.terminalapp.R
import com.zeller.terminalapp.databinding.ActivityMainBinding
import com.zeller.terminalapp.transactionList.TransactionsListView
import com.zeller.terminalapp.transactions.Transactions

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.depositButton.setOnClickListener(this)
        binding.withdrawButton.setOnClickListener(this)
        binding.historyButton.setOnClickListener(this)
        setContentView(binding.root)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.withdrawButton -> {
                getAmount()?.let { amt ->
                    val balance = MainViewModel.balance
                    if (balance >= amt) {
                        MainViewModel.balance -= amt
                        binding.balance.text = MainViewModel.balance.toString()
                        MainViewModel.transactions.addTransaction(
                            Transactions(
                                isDeposit = false,
                                amount = amt
                            )
                        )
                    } else {
                        Toast.makeText(this, "Not enough balance", Toast.LENGTH_LONG).show()
                    }
                }
            }

            R.id.depositButton -> {
                getAmount()?.let { amt ->
                    MainViewModel.balance += amt
                    binding.balance.text = MainViewModel.balance.toString()
                    MainViewModel.transactions.addTransaction(
                        Transactions(
                            isDeposit = true,
                            amount = amt
                        )
                    )
                }
            }

            R.id.historyButton -> {
                startActivity(Intent(this, TransactionsListView::class.java))
            }

            else -> {}
        }
    }

    private fun getAmount() = try {
        binding.amountInput.text?.toString()?.toFloat()
    } catch (_: Exception) {
        null
    }
}

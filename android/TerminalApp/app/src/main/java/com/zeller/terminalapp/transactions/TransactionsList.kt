package com.zeller.terminalapp.transactions

class TransactionsList {
    private val transactionsList: MutableList<Transactions> = mutableListOf()
    fun addTransaction(transactions: Transactions) {
        transactionsList.add(transactions)
    }

    fun getTransactions() = transactionsList.toList()
}
package com.zeller.terminalapp

import com.zeller.terminalapp.transactions.TransactionsList

object MainViewModel {
    var balance = 0.0f
    var transactions: TransactionsList = TransactionsList()
}
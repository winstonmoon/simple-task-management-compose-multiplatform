package com.winstonmoon.simpletaskmanagement.data.local

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher

class DatabaseHelper(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {
}
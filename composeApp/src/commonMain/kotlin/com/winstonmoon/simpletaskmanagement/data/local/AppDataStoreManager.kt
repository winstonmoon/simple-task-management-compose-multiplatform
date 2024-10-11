package com.winstonmoon.simpletaskmanagement.data.local

import com.winstonmoon.simpletaskmanagement.Context
import com.winstonmoon.simpletaskmanagement.getData
import com.winstonmoon.simpletaskmanagement.putData

const val APP_DATASTORE = "com.winstonmoon.simpletaskmanagement"

class AppDataStoreManager(val context: Context) : AppDataStore {

    override suspend fun setValue(
        key: String,
        value: String
    ) {
        context.putData(key, value)
    }

    override suspend fun readValue(
        key: String,
    ): String? {
        return context.getData(key)
    }
}
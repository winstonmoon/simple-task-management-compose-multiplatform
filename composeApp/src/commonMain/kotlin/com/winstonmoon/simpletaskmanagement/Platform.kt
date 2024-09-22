package com.winstonmoon.simpletaskmanagement

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
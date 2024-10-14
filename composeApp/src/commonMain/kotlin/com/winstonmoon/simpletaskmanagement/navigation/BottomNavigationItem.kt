package com.winstonmoon.simpletaskmanagement.navigation

sealed class BottomNavigationItem(
//    val title: Int,
//    val icon: Int,
//    val screenRoute: String,
) {
    data object Task : BottomNavigationItem()
    data object Achievement : BottomNavigationItem()
    data object InputTask : BottomNavigationItem()
    data object Settings : BottomNavigationItem()
}
package com.suonk.tasks_manager.utils

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}

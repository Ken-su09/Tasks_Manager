package com.suonk.tasks_manager.utils

sealed class TaskOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : TaskOrder(orderType)
    class Color(orderType: OrderType) : TaskOrder(orderType)
}

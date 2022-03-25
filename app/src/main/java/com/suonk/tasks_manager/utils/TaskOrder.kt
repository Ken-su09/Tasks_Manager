package com.suonk.tasks_manager.utils

sealed class TaskOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : TaskOrder(orderType)
    class Color(orderType: OrderType) : TaskOrder(orderType)
    class Date(orderType: OrderType) : TaskOrder(orderType)

    fun copy(orderType: OrderType): TaskOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Color -> Color(orderType)
            is Date -> Date(orderType)
        }
    }
}

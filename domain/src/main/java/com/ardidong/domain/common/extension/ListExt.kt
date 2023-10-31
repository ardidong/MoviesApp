package com.ardidong.domain.common.extension

fun List<Int>.containsAllItems(otherList: List<Int>): Boolean {
    return otherList.all { it in this }
}
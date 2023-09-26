package com.ardidong.domain.common.extension

fun Int?.orMin() = this ?: Int.MIN_VALUE

fun Int?.orZero() = this ?: 0

fun Int.toDurationString() : String {
    val hour = this / 60
    val minute = this % 60

    return buildString{
        if(hour > 0 ) append("${hour}h ")
        if(minute > 0 ) append("${minute}m")
    }
}



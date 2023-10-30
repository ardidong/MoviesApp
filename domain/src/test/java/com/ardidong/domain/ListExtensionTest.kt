package com.ardidong.domain

import com.ardidong.domain.common.extension.containsAllItems
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ListExtensionTest {
    @Test
    fun `is list A contains all List B`() {
        val listA = listOf(1 ,2, 3)
        val listB = listOf(1, 3)

        assertEquals(listA.containsAllItems(listB), true)
    }

    @Test
    fun `is list A contains all List B with single item`() {
        val listA = listOf(1 ,3, 4)
        val listB = listOf(4)

        assertEquals(listA.containsAllItems(listB), true)
    }

    @Test
    fun `is list A Not all List B with some item included`() {
        val listA = listOf(1 ,2, 3)
        val listB = listOf(1, 3, 4)

        assertEquals(listA.containsAllItems(listB), false)
    }

    @Test
    fun `is list A Not all List B with none item included`() {
        val listA = listOf(1 ,2, 3)
        val listB = listOf(5 ,6 ,7)

        assertEquals(listA.containsAllItems(listB), false)
    }

    @Test
    fun `is list A Not all List B with none item`() {
        val listA = listOf(1 ,2, 3)
        val listB = listOf(1, 2 ,3, 4)

        assertEquals(listA.containsAllItems(listB), false)
    }
}
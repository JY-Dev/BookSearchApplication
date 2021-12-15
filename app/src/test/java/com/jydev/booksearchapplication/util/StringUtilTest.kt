package com.jydev.booksearchapplication.util

import org.junit.Test

class StringUtilTest {
    @Test
    fun `android-kotlin 가 주어졌을때 hasNotOperator true 를 반환해야한다`(){
        val query = "android-kotlin"
        assert(query.hasNotOperator())
    }

    @Test
    fun `android 가 주어졌을때 hasNotOperator false 를 반환해야한다`(){
        val query = "android"
        assert(query.hasNotOperator().not())
    }

    @Test
    fun `android|kotlin 가 주어졌을때 hasOrOperator true 를 반환해야한다`(){
        val query = "android|kotlin"
        assert(query.hasOrOperator())
    }

    @Test
    fun `android 가 주어졌을때 hasOrOperator false 를 반환해야한다`(){
        val query = "android"
        assert(query.hasOrOperator().not())
    }
}
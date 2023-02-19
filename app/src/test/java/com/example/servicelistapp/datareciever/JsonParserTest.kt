package com.example.servicelistapp.datareciever

import org.junit.Assert.*

import org.junit.Test

class JsonParserTest {

    @Test //An empty string
    fun parseJsonTest1() {
        val input: String = " "

        assertEquals(ArrayList<Service>(), JsonParser().parseJson(input))
    }

    @Test //A null string
    fun parseJsonTest2() {
        val input: String = ""

        assertEquals(ArrayList<Service>(), JsonParser().parseJson(input))
    }

    @Test //A wrong input type
    fun parseJsonTest3() {
        val input: String = "{sdf123}"

        assertEquals(ArrayList<Service>(), JsonParser().parseJson(input))
    }

    @Test //A wrong JSON format
    fun parseJsonTest4() {
        val input: String = "{\"items\":[]}"

        assertEquals(ArrayList<Service>(), JsonParser().parseJson(input))
    }


}



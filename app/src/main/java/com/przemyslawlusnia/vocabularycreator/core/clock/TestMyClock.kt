package com.przemyslawlusnia.vocabularycreator.core.clock

class TestMyClock : MyClock() {

    override val time: String
        get() = TEST_DATE

    companion object {

        val TEST_DATE = "1989-07-12"
    }

}

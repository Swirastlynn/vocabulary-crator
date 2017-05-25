package com.przemyslawlusnia.vocabularycreator.core.clock

import java.util.Date

open class MyClock {

    open val time: String
        get() = Date().toString()
}

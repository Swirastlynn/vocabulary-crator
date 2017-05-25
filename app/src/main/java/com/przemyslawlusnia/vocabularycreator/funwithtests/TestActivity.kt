package com.przemyslawlusnia.vocabularycreator.funwithtests

import android.os.Bundle
import android.widget.TextView
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication
import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock
import com.przemyslawlusnia.vocabularycreator.extension.bind
import javax.inject.Inject

class TestActivity : BaseActivity() {

    private val testTxt: TextView by bind(R.id.testTxt)

    @Inject
    lateinit var myClock: MyClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        VocabularyCreatorApplication.clockComponent.inject(this)
        setupToolbar()
        testTxt.text = myClock.time
    }

}
package com.przemyslawlusnia.vocabularycreator.funwithtests

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication
import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock
import javax.inject.Inject

class TestActivity : BaseActivity() {

    lateinit var testTxt: TextView

    @Inject
    lateinit var myClock: MyClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        VocabularyCreatorApplication.clockComponent.inject(this)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        testTxt = findViewById(R.id.testTxt) as TextView
        setupToolbar()
        testTxt.text = myClock.time
    }

}
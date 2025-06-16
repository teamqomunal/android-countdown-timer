package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.NumberFormat


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private val router: MainRouter by lazy {
        MainRouter(this)
    }

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initUI() {
        binding.apply {
            object : CountDownTimer(50000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Used for formatting digits to be in 2 digits only
                    val f: NumberFormat = DecimalFormat("00")
                    val hour = (millisUntilFinished / 3600000) % 24
                    val min = (millisUntilFinished / 60000) % 60
                    val sec = (millisUntilFinished / 1000) % 60
                    btnTest.text = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)
                }

                override fun onFinish() {
                    // When the task is over it will print 00:00:00
                    btnTest.text = "00:00:00"
                }
            }.start()
        }
    }

    override fun initObserver() {
        viewModel.apply {

        }
    }

}
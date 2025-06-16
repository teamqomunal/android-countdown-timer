package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.common.ext.ISO_FORMAT
import com.qomunal.opensource.androidresearch.common.ext.countdownTimerExt
import com.qomunal.opensource.androidresearch.common.ext.fromIsoDateUTC
import com.qomunal.opensource.androidresearch.common.ext.getMillisExt
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding


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

            val dates = "2025-06-16T08:54:12.000Z"

            Log.d("Stechu", "dates: $dates")
            Log.d("Stechu", "dates: ${dates.fromIsoDateUTC()}")
            Log.d("Stechu", "dates: ${dates.getMillisExt(ISO_FORMAT)}")

            tvText.countdownTimerExt(dates.getMillisExt(ISO_FORMAT))
        }
    }

    override fun initObserver() {
        viewModel.apply {

        }
    }

}
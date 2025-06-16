package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.common.ext.DATE_TIME_FORMAT
import com.qomunal.opensource.androidresearch.common.ext.ISO_FORMAT
import com.qomunal.opensource.androidresearch.common.ext.SIMPLE_DATE_FORMAT
import com.qomunal.opensource.androidresearch.common.ext.countdownTimerExt
import com.qomunal.opensource.androidresearch.common.ext.fromIsoDateUTC
import com.qomunal.opensource.androidresearch.common.ext.getDateTodayExt
import com.qomunal.opensource.androidresearch.common.ext.getMillisExt
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding
import java.util.Calendar


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
            val today = getDateTodayExt(ISO_FORMAT)

            val currentTime = Calendar.getInstance().time

            val due = dates.getMillisExt(ISO_FORMAT) - currentTime.time

            Log.d("CountDown", "today: $today")
            Log.d("CountDown", "today: ${today.fromIsoDateUTC()}")

            Log.d("CountDown", "dates: $dates")
            Log.d("CountDown", "dates: ${dates.fromIsoDateUTC()}")

            Log.d("CountDown", "today: ${today.getMillisExt(ISO_FORMAT)}")
            Log.d("CountDown", "dates: ${dates.getMillisExt(ISO_FORMAT)}")
            Log.d("CountDown", "due: $due")

            tvToday.countdownTimerExt(today.getMillisExt(ISO_FORMAT))
            tvDate.countdownTimerExt(dates.getMillisExt(ISO_FORMAT))
            tvDue.countdownTimerExt(due)
        }
    }

    override fun initObserver() {
        viewModel.apply {

        }
    }

}
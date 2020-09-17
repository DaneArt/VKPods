package com.daneart.vkpods

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.daneart.vkpods.customView.TimeCutterView
import com.daneart.vkpods.customView.TimeLineSlider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_fragment.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var startX = 0f
    private var endX = 0f

    private val speedScroll = 0.5

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
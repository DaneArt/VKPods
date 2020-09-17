package com.daneart.vkpods

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.daneart.vkpods.customView.TimeCutterView
import com.daneart.vkpods.customView.TimeLineSlider
import kotlinx.android.synthetic.main.edit_fragment.*
import kotlin.random.Random

class EditFragment : Fragment() {

    private var startX = 0f
    private var endX = 0f
    private val speedScroll = 0.5f

    companion object {
        fun newInstance() = EditFragment()
    }

    private lateinit var viewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        btn_editNext.setOnClickListener {
            it.findNavController().navigate(R.id.action_editFragment_to_publishFragment)
        }

        imageView5.setOnClickListener {
            it.findNavController().navigate(R.id.action_editFragment_to_musicFragment)

        }
        var count = 60
        mainTimeLine.volumeList = generateSequence {
            if (count-- > 0) {
                Random.nextDouble(0.0, 1.0).toFloat()
            } else {
                null
            }

        }.toList()



        timeLineSlider.setOnTouchListener { v, event ->
            val view = v as TimeLineSlider
            if (event.x in view.posX - 32f..view.posX + 32f) {
                view.posX = event.x
                view.invalidate()

            } else {
                val action = event.action

                when (action) {

                    MotionEvent.ACTION_DOWN -> {
                        startX = event.rawX
                        Log.d("Offset", "StartX: $startX")
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        endX = event.rawX
                        val offset = (endX - startX) * speedScroll.toFloat()
                        Log.d("Offset", "EndX: $endX")

                        Log.d("Offset", "Offset: $offset")

                        topTimeLineView.setOffset(offset)
                        mainTimeLine.setOffset(offset)
                    }
                    MotionEvent.ACTION_UP -> {
                        endX = event.rawX
                        val offset = (endX - startX) * speedScroll.toFloat()
                        Log.d("Offset", "EndX: $endX")

                        Log.d("Offset", "Offset: $offset")

                        topTimeLineView.setOffset(offset)
                        mainTimeLine.setOffset(offset)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        endX = event.rawX
                        val offset = (endX - startX) * speedScroll.toFloat()
                        Log.d("Offset", "EndX: $endX")

                        Log.d("Offset", "Offset: $offset")

                        topTimeLineView.setOffset(offset)
                        mainTimeLine.setOffset(offset)
                    }
                }
            }

            true
        }


        timeCutter.setOnTouchListener { v, ev ->
            val view = v as TimeCutterView
            if (ev.x in view.startX - 32f..view.startX + 32f) {
                view.startX = ev.x
                view.invalidate()
            } else if (ev.x in view.endX - 32f..view.endX + 32f) {
                view.setEndX(ev.x)
                view.invalidate()
            } else {
                val action = ev.action

                when (action) {

                    MotionEvent.ACTION_DOWN -> {
                        startX = ev.rawX
                        Log.d("Offset", "StartX: $startX")
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        endX = ev.rawX
                        val offset = (endX - startX) * speedScroll.toFloat()
                        Log.d("Offset", "EndX: $endX")

                        Log.d("Offset", "Offset: $offset")

                        topTimeLineView.setOffset(offset)
                        mainTimeLine.setOffset(offset)
                    }
                    MotionEvent.ACTION_UP -> {
                        endX = ev.rawX
                        val offset = (endX - startX) * speedScroll.toFloat()
                        Log.d("Offset", "EndX: $endX")

                        Log.d("Offset", "Offset: $offset")

                        topTimeLineView.setOffset(offset)
                        mainTimeLine.setOffset(offset)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        endX = ev.rawX
                        val offset = (endX - startX) * speedScroll.toFloat()
                        Log.d("Offset", "EndX: $endX")

                        Log.d("Offset", "Offset: $offset")

                        topTimeLineView.setOffset(offset)
                        mainTimeLine.setOffset(offset)
                    }
                }
            }
            true
        }


    }

}
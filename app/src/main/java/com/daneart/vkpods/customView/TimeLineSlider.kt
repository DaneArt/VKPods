package com.daneart.vkpods.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent.INVALID_POINTER_ID
import android.view.View
import android.widget.LinearLayout

class TimeLineSlider(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val sliderPaint = Paint()
    private val trianglePath = Path()

    var posX = 0f
        set(value) {
            if(value in 0f..width.toFloat()-32f){
                field = value
            }

        }

    init {
        sliderPaint.color = Color.argb(0xFF, 0xFF, 0x33, 0x47)
        sliderPaint.strokeWidth = 8f
        sliderPaint.style = Paint.Style.FILL_AND_STROKE
        sliderPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            LinearLayout.resolveSize(desiredWidth, widthMeasureSpec),
            LinearLayout.resolveSize(desiredHeight, heightMeasureSpec)
        );
    }

    // The ‘active pointer’ is the one currently moving our object.
    private var mActivePointerId = INVALID_POINTER_ID


    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            trianglePath.reset()
            trianglePath.rewind()
            trianglePath.moveTo(posX, 0f)
            trianglePath.lineTo(posX + 24f, 0f)
            trianglePath.lineTo(posX + 12f, 24f)
            trianglePath.close()

            canvas.drawPath(trianglePath, sliderPaint)
            canvas.drawLine(posX + 12f, 12f, posX + 12f, height.toFloat(), sliderPaint)


        }
    }
}
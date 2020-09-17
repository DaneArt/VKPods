package com.daneart.vkpods.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.daneart.vkpods.extensions.dpToPix

class MainTimeLineView(context: Context, attrs: AttributeSet) :
    View(context, attrs) {

    private val linePaint: Paint = Paint()

    @JvmField
    var volumeList: List<Float> = listOf()
    private val lineGap = 20f
    private var customOffset = 6f

    fun setOffset(offset: Float) {

        customOffset = if (offset < 0) {
            kotlin.math.min(customOffset + kotlin.math.abs(offset), volumeList.size*lineGap)

        } else {
            kotlin.math.max(customOffset - offset, 0f)

        }
        invalidate()
    }

    init {
        linePaint.color = Color.argb(0xff, 0x3f, 0x8a, 0xe0)
        linePaint.strokeWidth = 18f
        linePaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            LinearLayout.resolveSize(desiredWidth, widthMeasureSpec),
            LinearLayout.resolveSize(desiredHeight, heightMeasureSpec)
        );
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            val startIndex = customOffset.toInt() / (lineGap + linePaint.strokeWidth).toInt()
            for ((index, line) in volumeList.subList(startIndex, volumeList.size).withIndex()) {

                canvas.drawLine(
                    (index + 1) * (lineGap + linePaint.strokeWidth),
                    height / 2f + line * height / 4f,
                    (index + 1) * (lineGap + linePaint.strokeWidth),
                    height / 2f - line * height / 4f,
                    linePaint
                )
            }
        }
    }


}
package com.daneart.vkpods.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.daneart.vkpods.R

class TopTimeLineView(
    context: Context, attrs: AttributeSet
) :
    View(context, attrs) {

    private val timePaint: Paint = Paint()
    private val textPaint: Paint = Paint()
    private val pickerPaint: Paint = Paint()


    private var seconds = 0
    private var smallPointHeight = 8f
    private var mediumPointHeight = 16f
    private var bigPointHeight = 36f
    private var paintColor =
        Color.argb(0xff, 0x99, 0xA2, 0xAD)
    private var textSize = 36f
    private var pointsGap = 20f
    private val pointWidth = 6f

    private var customOffset = 6f

    fun setOffset(offset: Float) {

        customOffset = if (offset < 0) {
            kotlin.math.min(customOffset + kotlin.math.abs(offset), seconds * pointWidth/2)

        } else {
            kotlin.math.max(customOffset - offset, 0f)

        }
        invalidate()
    }

    init {

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TopTimeLineView,
            0, 0
        ).apply {
            try {
                seconds = getInteger(R.styleable.TopTimeLineView_seconds, 0)
                smallPointHeight = getDimension(R.styleable.TopTimeLineView_smallPointHeight, 0f)
                mediumPointHeight = getDimension(R.styleable.TopTimeLineView_mediumPointHeight, 0f)
                bigPointHeight = getDimension(R.styleable.TopTimeLineView_bigPointHeight, 0f)
                paintColor = getColor(
                    R.styleable.TopTimeLineView_mainColor,
                    Color.argb(0xff, 0x99, 0xA2, 0xAD)
                )
                textSize = getDimension(R.styleable.TopTimeLineView_infoTextSize, 36f)
                pointsGap = getDimension(R.styleable.TopTimeLineView_pointsGap, 20f)
            } finally {
                recycle()
            }
        }
        //setup timePaint
        timePaint.color = paintColor
        timePaint.strokeWidth = pointWidth
        timePaint.style = Paint.Style.STROKE
        timePaint.strokeCap = Paint.Cap.ROUND


        //setup textPaint
        textPaint.color = paintColor
        textPaint.style = Paint.Style.STROKE
        textPaint.textSize = textSize
        textPaint.typeface = Typeface.MONOSPACE

        //setup pickerPaint

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )

    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.let { drawTopLine(it) }
    }

    private fun drawTopLine(canvas: Canvas) {
        try {
            val maxPoints = width / 6//point.width+gap

            val points = kotlin.math.min(maxPoints, seconds)
            val gap = pointsGap + pointWidth
            Log.d("TAG", "Points: $points")
            val startY = height.toFloat() - paddingBottom
            val startIndex = customOffset.toInt() / 6
            val end = kotlin.math.min(points+startIndex, seconds)
            for (index in startIndex..end) {
                when {
                    index % 10 == 0 -> {
                        canvas.drawLine(
                            (index-startIndex+1) * gap,
                            startY,
                            (index-startIndex+1) * gap,
                            startY - bigPointHeight,
                            timePaint
                        )
                        val minutes = index / 60
                        val seconds = index - minutes * 60

                        canvas.drawText(
                            "$minutes:$seconds",
                            (index-startIndex+1) * gap - minutes.toString().length * textPaint.textSize,
                            startY - bigPointHeight - textPaint.textSize / 2,
                            textPaint
                        )
                        Log.d("TAG", "Drawn a big line")
                    }
                    index % 2 == 0 -> {
                        canvas.drawLine(
                            (index-startIndex+1) * gap,
                            startY,
                            (index-startIndex+1) * gap,
                            startY - mediumPointHeight,
                            timePaint
                        )

                        Log.d("TAG", "Drawn a medium line")
                    }
                    else -> {
                        canvas.drawLine(
                            (index-startIndex+1) * gap,
                            startY,
                            (index-startIndex+1) * gap,
                            startY - smallPointHeight,
                            timePaint
                        )
                        Log.d("TAG", "Drawn a small line")
                    }
                }
            }
        } catch (e: Throwable) {
            Log.e("TAG", "Time line view: ${e.message}")
        }
    }

}
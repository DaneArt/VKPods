package com.daneart.vkpods.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.daneart.vkpods.R

class TimeCutterView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var startX: Float = 32f
        set(value) {
            if (value in 32f..endX) {
                field = value
            }else if(
                value < 32f
                    ) {
                field = 32f
            }
        }

    @JvmField
    var endX: Float = 0f

    fun setEndX(value: Float) {
        if(value in startX..width.toFloat()-32f){
            endX = value
        }

    }

    private val controlPaint: Paint = Paint()
    private val whitePaint: Paint = Paint()
    private val borderPaint = Paint()

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TimeCutterView,
            0, 0
        ).apply {
            try {
                startX = getDimension(R.styleable.TimeCutterView_startX, 32f)
                endX = getDimension(R.styleable.TimeCutterView_endX, startX + 32f)
                if (endX < startX) {
                    throw IllegalArgumentException()
                }
            } finally {
                recycle()
            }
        }
        controlPaint.apply {
            color = Color.argb(0xFF, 0x3F, 0x8A, 0xE0)
        }

        whitePaint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 8f
            color = Color.WHITE
        }
        borderPaint.apply {
            color = Color.argb(0xFF, 0x3F, 0x8A, 0xE0)
            strokeWidth = 8f
            style = Paint.Style.STROKE
        }
    }


    constructor(context: Context, attrs: AttributeSet, startX: Float, endX: Float) : this(
        context,
        attrs
    ) {
        this.startX = startX
        this.endX = endX
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = endX.toInt() - startX.toInt() + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            LinearLayout.resolveSize(desiredWidth, widthMeasureSpec),
            LinearLayout.resolveSize(desiredHeight, heightMeasureSpec)
        );
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            //draw start control
            drawRect(startX - 16f, 0f, startX + 16f, height.toFloat(), controlPaint)
            drawLine(startX, height / 2f - 16f, startX, height / 2f + 16f, whitePaint)

            //draw start control
            drawRect(endX - 16f, 0f, endX + 16f, height.toFloat(), controlPaint)
            drawLine(endX, height / 2f - 16f, endX, height / 2f + 16f, whitePaint)


            //draw gap
            drawRect(startX - 16f, 0f, endX + 16f, height.toFloat(), borderPaint)
        }
    }
}
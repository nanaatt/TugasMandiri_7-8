package com.project.tugaslanjutan

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class Page1(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val radius = 140f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val textPaint = createTextPaint(Color.BLACK, 50f, Typeface.BOLD, Paint.Align.LEFT)
    private val normalTextPaint = createTextPaint(Color.DKGRAY, 40f, Typeface.NORMAL, Paint.Align.LEFT)
    private val bioTextPaint = createTextPaint(Color.BLACK, 40f, Typeface.BOLD, Paint.Align.LEFT)
    private val linkTextPaint = createTextPaint(Color.parseColor("#1DA1F2"), 40f, Typeface.BOLD, Paint.Align.LEFT)

    private val vectorDrawables: Array<Drawable> = arrayOf(
        ContextCompat.getDrawable(context, R.drawable.bashline_badge)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_locat)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_calender)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_link)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_back)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_more)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_plus)!!
    )

    private val buttonRect = RectF()
    private val plusRect = RectF()
    private var isPlusPressed = false

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)

        drawHeader(canvas)
        drawProfileSection(canvas)
        drawProfileInfo(canvas)
        drawFollowSection(canvas)
        drawEditButton(canvas)
        drawPlusButton(canvas)
    }

    private fun drawHeader(canvas: Canvas) {
        paint.color = Color.BLUE
        canvas.drawRect(0f, 0f, width.toFloat(), 330f, paint)

        drawDrawable(canvas, vectorDrawables[4], 45f, 40f, 70, 70)
        drawDrawable(canvas, vectorDrawables[5], width - 85f, 40f, 70, 70)
    }

    private fun drawProfileSection(canvas: Canvas) {
        val circleX = radius + 30f
        val circleY = 320f
        paintCircle.color = Color.parseColor("#D3D3D3")
        canvas.drawCircle(circleX, circleY, radius, paintCircle)

        val textX = circleX - radius
        canvas.drawText("Careen", textX, circleY + radius + 60f, textPaint)
        canvas.drawText("@Careenemilza", textX, circleY + radius + 115f, normalTextPaint)
        canvas.drawText("Hidup dengan impian besar.", textX, circleY + radius + 185f, bioTextPaint)
    }

    private fun drawProfileInfo(canvas: Canvas) {
        val textX = radius - 110f
        val circleY = 320f
        val profileBaseY = circleY + radius + 220f

        val profileInfo = listOf(
            vectorDrawables[0] to "Advertising & Marketing Agency",
            vectorDrawables[1] to "Surabaya, Indonesia",
            vectorDrawables[2] to "Joined June, 2023"
        )

        profileInfo.forEachIndexed { index, (icon, text) ->
            val yPosition = profileBaseY + (index * 70f)
            drawDrawable(canvas, icon, textX, yPosition, 50, 50)
            canvas.drawText(text, textX + 70f, yPosition + 40f, normalTextPaint)
        }

        drawDrawable(canvas, vectorDrawables[3], textX + 480f, profileBaseY + 70f, 50, 50)
        canvas.drawText("linktr.ee/worldwidehuh..", textX + 550f, profileBaseY + 110f, linkTextPaint)
    }

    private fun drawFollowSection(canvas: Canvas) {
        val textX = radius - 110f
        val baseY = 320f + radius + 470f

        val followData = listOf(
            "1,156" to "Following",
            "2,200" to "Followers"
        )

        var xPosition = textX
        followData.forEach { (number, label) ->
            canvas.drawText(number, xPosition + 20f, baseY, bioTextPaint)
            canvas.drawText(label, xPosition + 140f, baseY, normalTextPaint)
            xPosition += 330f
        }
    }

    private fun drawEditButton(canvas: Canvas) {
        val btnEditX = width - 380f
        val btnEditY = 380f
        val btnEditWidth = 350f
        val btnEditHeight = 100f

        buttonRect.set(btnEditX, btnEditY, btnEditX + btnEditWidth, btnEditY + btnEditHeight)

        paint.apply {
            color = Color.LTGRAY
            style = Paint.Style.STROKE
            strokeWidth = 3f
        }
        canvas.drawRoundRect(buttonRect, 50f, 50f, paint)

        paint.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            textSize = 40f
        }
        val buttonText = "Edit Profile"
        val textWidth = paint.measureText(buttonText)
        canvas.drawText(buttonText, buttonRect.centerX() - textWidth / 2, buttonRect.centerY() + 15f, paint)
    }

    private fun drawPlusButton(canvas: Canvas) {
        val plusSize = 250
        val margin = 40f
        val x = width - plusSize - margin
        val y = height - plusSize - margin

        plusRect.set(x, y, x + plusSize, y + plusSize)

        paint.color = if (isPlusPressed) Color.LTGRAY else Color.WHITE
        paint.style = Paint.Style.FILL
        canvas.drawCircle(plusRect.centerX(), plusRect.centerY(), plusSize / 2f, paint)

        paint.color = Color.parseColor("#FFFFFF")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawCircle(plusRect.centerX(), plusRect.centerY(), plusSize / 2f, paint)

        val iconSize = 200
        val iconX = plusRect.centerX() - iconSize / 2
        val iconY = plusRect.centerY() - iconSize / 2
        drawDrawable(canvas, vectorDrawables[6], iconX, iconY, iconSize, iconSize)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val plusSize = 250
        val margin = 40f
        val x = width - plusSize - margin
        val y = height - plusSize - margin
        plusRect.set(x, y, x + plusSize, y + plusSize)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (plusRect.contains(event.x, event.y)) {
                    isPlusPressed = true
                    invalidate()
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                if (isPlusPressed) {
                    isPlusPressed = false
                    invalidate()
                    if (plusRect.contains(event.x, event.y)) {
                        performClick()
                        openPostingActivity()
                    }
                    return true
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                isPlusPressed = false
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    private fun openPostingActivity() {
        context.startActivity(Intent(context, PostingActivity::class.java))
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    private fun drawDrawable(canvas: Canvas, drawable: Drawable, x: Float, y: Float, width: Int, height: Int) {
        drawable.setBounds(x.toInt(), y.toInt(), x.toInt() + width, y.toInt() + height)
        drawable.draw(canvas)
    }

    private fun createTextPaint(color: Int, textSize: Float, style: Int, align: Paint.Align): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            this.textSize = textSize
            textAlign = align
            typeface = Typeface.create(Typeface.DEFAULT, style)
        }
    }
}
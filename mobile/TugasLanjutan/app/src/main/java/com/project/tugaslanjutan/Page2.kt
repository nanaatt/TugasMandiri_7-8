package com.project.tugaslanjutan

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

class Page2(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val inputText = StringBuilder()

    private val closeButtonRect = RectF()
    private val postButtonRect = RectF()

    private val vectorProfile = ContextCompat.getDrawable(context, R.drawable.bashline_galery)!!
    private val vectorBack = ContextCompat.getDrawable(context, R.drawable.bashline_gif)!!

    private val icons = listOf(
        vectorProfile,
        vectorBack,
        ContextCompat.getDrawable(context, R.drawable.bashline_vote)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_loc)!!,
        ContextCompat.getDrawable(context, R.drawable.bashline_plusnew)!!
    )

    private lateinit var iconBitmaps: List<Bitmap>

    init {
        val iconSizePx = (72f * resources.displayMetrics.density).toInt()
        iconBitmaps = icons.map { drawable ->
            drawable.toBitmap(iconSizePx, iconSizePx, null).apply {
                if (isRecycled || width <= 0 || height <= 0) {
                    Log.e("Page2", "Failed to create bitmap for drawable")
                }
            }
        }.filter { !it.isRecycled && it.width > 0 && it.height > 0 }

        if (iconBitmaps.size != icons.size) {
            Log.w("Page2", "Only ${iconBitmaps.size} of ${icons.size} icons loaded successfully")
        }

        setBackgroundColor(Color.parseColor("#10000000"))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawCloseButton(canvas)
        drawPostButton(canvas)
        drawInputText(canvas)
        drawIcons(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }

    private fun drawCloseButton(canvas: Canvas) {
        paint.color = Color.BLACK
        val closeX = 16f
        val closeY = 50f
        val closeSize = 100f
        paint.textSize = 80f
        canvas.drawText("✕", closeX, closeY + closeSize / 2, paint)
        closeButtonRect.set(closeX, closeY - closeSize / 2, closeX + closeSize, closeY + closeSize / 2)
    }

    private fun drawPostButton(canvas: Canvas) {
        val buttonX = width - 300f
        val buttonY = 40f
        paint.color = Color.parseColor("#1d9bf0")
        val buttonWidth = 250f
        val buttonHeight = 100f
        canvas.drawRoundRect(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight, 30f, 30f, paint)
        paint.color = Color.WHITE
        paint.textSize = 60f
        canvas.drawText("Posting", buttonX + 20f, buttonY + (buttonHeight / 2) + 20f, paint)
        postButtonRect.set(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight)
    }

    private fun drawInputText(canvas: Canvas) {
        paint.textSize = 55f

        if (inputText.isEmpty()) {
            // Placeholder di tengah layar
            paint.color = Color.GRAY
            val placeholder = "Apa yang sedang terjadi?"
            val textWidth = paint.measureText(placeholder)
            val x = (width - textWidth) / 2
            val y = (height / 2) - ((paint.descent() + paint.ascent()) / 2)
            canvas.drawText(placeholder, x, y, paint)
        } else {
            // Tampilkan input user di kiri atas
            paint.color = Color.BLACK
            val lines = inputText.toString().split("\n")
            val x = 50f
            var y = 250f

            for (line in lines) {
                canvas.drawText(line, x, y, paint)
                y += paint.textSize + 20f
            }
        }
    }

    private fun drawIcons(canvas: Canvas) {
        if (iconBitmaps.isEmpty()) return

        val iconSize = 100f
        val iconMargin = 20f
        val iconSpacing = 30f
        val iconY = height - iconSize - 1f

        val totalWidth = (iconSize * iconBitmaps.size) +
                (iconSpacing * (iconBitmaps.size - 1)) +
                (iconMargin * 2)

        val actualSpacing = if (iconBitmaps.size > 1 && totalWidth > width) {
            (width - (iconSize * iconBitmaps.size) - (iconMargin * 2)) / (iconBitmaps.size - 1)
        } else {
            iconSpacing
        }

        var iconX = iconMargin

        for (bitmap in iconBitmaps) {
            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, iconSize.toInt(), iconSize.toInt(), false)
            canvas.drawBitmap(resizedBitmap, iconX, iconY - iconSize, paint)
            iconX += iconSize + actualSpacing
        }

        val plusSize = 100f
        val plusX = width - 150f
        val plusY = height - 150f

        paint.color = Color.parseColor("#1d9bf0")
        canvas.drawCircle(plusX, plusY, plusSize / 2, paint)

        paint.color = Color.WHITE
        paint.textSize = 90f
        val textBounds = Rect()
        paint.getTextBounds("+", 0, 1, textBounds)

        val offsetX = 0f
        val offsetY = 5f

        canvas.drawText("+", plusX - (textBounds.width() / 2) + offsetX, plusY + (textBounds.height() / 2) + offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (closeButtonRect.contains(event.x, event.y)) {
                Toast.makeText(context, "Kembali", Toast.LENGTH_SHORT).show()
                (context as? android.app.Activity)?.finish() // kembali ke Page1
                return true
            } else if (postButtonRect.contains(event.x, event.y)) {
                if (inputText.isNotEmpty()) {
                    // Simpan ke database SQLite
                    val dbHelper = PostDatabaseHelper(context)
                    val result = dbHelper.insertPost(inputText.toString())

                    if (result != -1L) {
                        Toast.makeText(context, "Postingan berhasil diunggah!", Toast.LENGTH_SHORT).show()

                        // Delay untuk menunggu Toast sebelum kembali
                        postDelayedFinish()
                    } else {
                        Toast.makeText(context, "Gagal mengunggah postingan!", Toast.LENGTH_SHORT).show()
                    }
                    return true
                } else {
                    Toast.makeText(context, "Isi postingan terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return true
                }
            }
        }
        return true
    }

    private fun postDelayedFinish() {
        postDelayed({
            (context as? android.app.Activity)?.finish()
        }, 1500) // 1.5 detik delay agar Toast sempat terlihat
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        iconBitmaps.forEach { it.recycle() }
    }

    fun updateInputText(text: String) {
        inputText.clear()
        inputText.append(text)
        invalidate()
    }
}
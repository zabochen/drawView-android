package ua.ck.zabochen.android.drawview

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.StringBuilder

class PaintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutView(this))
    }

    class LayoutView(context: Context) : View(context) {

        private val paint: Paint = Paint()
        private val rect: Rect = Rect()
        private val rectF: RectF = RectF(700f, 100f, 800f, 150f)

        // 5 points (x:100 y:50) (x:150 y:100)...
        private val points1 =
            floatArrayOf(100f, 50f, 150f, 100f, 150f, 200f, 50f, 200f, 50f, 100f)
        private val points2 =
            floatArrayOf(300f, 200f, 600f, 200f, 300f, 300f, 600f, 300f, 400f, 100f, 400f, 400f, 500f, 100f, 500f, 400f)

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            canvas?.apply {

                // Paint: Settings
                paint.apply {
                    color = ContextCompat.getColor(context, R.color.colorAccent)
                    strokeWidth = 1f
                }

                // Canvas: Background - drawRGB() / drawARGB() / drawColor()
                drawColor(Color.parseColor("#6200EE"))

                // Drawing
                drawMultiple(this, paint)
            }
        }

        private fun drawSingle(canvas: Canvas, paint: Paint) = with(canvas) {
            // Point
            drawPoint(50f, 50f, paint)

            // Line
            drawLine(100f, 100f, 500f, 50f, paint)

            // Circle
            drawCircle(200f, 200f, 50f, paint)

            // Rect v1 - leftTop/rightBottom dots
            drawRect(300f, 150f, 400f, 200f, paint)

            // Rect v2
            rect.set(400, 300, 500, 600)
            // We can send Rect with int values / RectF with float values
            canvas.drawRect(rect, paint)
        }

        private fun drawMultiple(canvas: Canvas, paint: Paint) = with(canvas) {
            // Points
            drawPoints(points1, paint)
            //drawPoints(points2, paint)

            // Lines
            drawLines(points2, paint)

            // Rect with rounded corners
            drawRoundRect(rectF, 30f, 30f, paint)
            // Offset
            // Before - RectF(700f, 100f, 800f, 150f)
            // After - RectF(700f + 0, 100f + 150, 800f + 0, 150f + 150)
            rectF.offset(0f, 150f)
            canvas.drawRect(rectF, paint)

            // OffsetTo
            // Note:
            // This method don't use old coordinates
            // and set new TopLeft point
            rectF.offsetTo(900f, 100f)
            drawOval(rectF, paint)

            // Increase rect
            rectF.offsetTo(900f, 300f)

            // Inset
            // positive - making the rectangle narrower
            // negative - making the rectangle wider
            rectF.inset(0f, -25f)
            drawArc(rectF, 0f, 90f, true, paint)
            paint.color = Color.RED
            rectF.inset(0f, 25f)
            drawArc(rectF, 90f, 360f, true, paint)

            // Works with arcs
            // Start 0 = 3 hour -> next for clockwise
            val rectForArc = RectF(50f, 400f, 250f, 600f)
            drawArc(rectForArc, 90f, 270f, false, paint)
            rectForArc.inset(50f, 50f)
            paint.color = Color.BLUE
            drawArc(rectForArc, 0f, 90f, true, paint)

            // Text
            // Default align - Paint.Align.LEFT
            paint.apply {
                strokeWidth = 10f
                color = Color.BLUE
                drawPoint(100f, 300f, this)
            }

            paint.apply {
                strokeWidth = 5f
                textSize = 30f
                color = Color.GREEN
                isAntiAlias = true
                drawText("SomeText_1", 100f, 300f, this)
            }

            // Center
            paint.apply {
                textAlign = Paint.Align.CENTER
                drawText("SomeText_2", 100f, 320f, this)
            }

            // Right
            paint.apply {
                textAlign = Paint.Align.RIGHT
                drawText("SomeText_3", 100f, 340f, this)
            }

            // Canvas Size
            val canvasSizeText = StringBuilder().also {
                it.append("Canvas size => width: ")
                it.append(canvas.width)
                it.append(", height: ")
                it.append(canvas.height)
            }
            drawText(canvasSizeText.toString(), 100f, 160f, paint.also {
                it.textAlign = Paint.Align.LEFT
            })

            // Paint Style
            // Default - Paint.Style.FILL.
            val rectWithPaintStyle = Rect(50, 50, 100, 100)

            // Fill
            paint.apply {
                style = Paint.Style.FILL
                color = Color.RED
            }
            canvas.drawRect(rectWithPaintStyle, paint)

            // Stroke
            paint.style = Paint.Style.STROKE
            rectWithPaintStyle.offset(100, 0)
            canvas.drawRect(rectWithPaintStyle, paint)

            // Fill & Stroke
            paint.style = Paint.Style.FILL_AND_STROKE
            rectWithPaintStyle.offset(100, 0)
            canvas.drawRect(rectWithPaintStyle, paint)
        }
    }
}
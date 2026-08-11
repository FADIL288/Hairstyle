package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.data.model.FaceShape
import com.example.ui.theme.GoldPrimary

@Composable
fun FaceOutlineOverlay(
    faceShape: FaceShape,
    guideColor: Color = GoldPrimary,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        val strokeStyle = Stroke(
            width = 3.5f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)
        )
        val solidStroke = Stroke(width = 2.5f)

        val path = Path()

        when (faceShape) {
            FaceShape.OVAL -> {
                val rx = w * 0.34f
                val ry = h * 0.38f
                drawOval(
                    color = guideColor,
                    topLeft = Offset(cx - rx, cy - ry),
                    size = Size(rx * 2, ry * 2),
                    style = strokeStyle
                )
            }

            FaceShape.ROUND -> {
                val r = (w * 0.36f).coerceAtMost(h * 0.36f)
                drawCircle(
                    color = guideColor,
                    center = Offset(cx, cy),
                    radius = r,
                    style = strokeStyle
                )
            }

            FaceShape.SQUARE -> {
                val halfW = w * 0.34f
                val halfH = h * 0.34f
                path.moveTo(cx - halfW + 30f, cy - halfH)
                path.lineTo(cx + halfW - 30f, cy - halfH)
                path.quadraticBezierTo(cx + halfW, cy - halfH, cx + halfW, cy - halfH + 30f)
                path.lineTo(cx + halfW, cy + halfH - 30f)
                path.quadraticBezierTo(cx + halfW, cy + halfH, cx + halfW - 30f, cy + halfH)
                path.lineTo(cx - halfW + 30f, cy + halfH)
                path.quadraticBezierTo(cx - halfW, cy + halfH, cx - halfW, cy + halfH - 30f)
                path.lineTo(cx - halfW, cy - halfH + 30f)
                path.quadraticBezierTo(cx - halfW, cy - halfH, cx - halfW + 30f, cy - halfH)
                path.close()
                drawPath(path = path, color = guideColor, style = strokeStyle)
            }

            FaceShape.HEART -> {
                val topW = w * 0.36f
                val topY = cy - h * 0.36f
                val chinY = cy + h * 0.38f
                path.moveTo(cx, topY + 25f)
                path.cubicTo(cx - 30f, topY - 10f, cx - topW, topY, cx - topW, cy - 20f)
                path.cubicTo(cx - topW, cy + 80f, cx - 40f, chinY - 40f, cx, chinY)
                path.cubicTo(cx + 40f, chinY - 40f, cx + topW, cy + 80f, cx + topW, cy - 20f)
                path.cubicTo(cx + topW, topY, cx + 30f, topY - 10f, cx, topY + 25f)
                path.close()
                drawPath(path = path, color = guideColor, style = strokeStyle)
            }

            FaceShape.DIAMOND -> {
                val foreheadW = w * 0.22f
                val cheekW = w * 0.38f
                val chinW = w * 0.12f
                val topY = cy - h * 0.36f
                val chinY = cy + h * 0.38f

                path.moveTo(cx - foreheadW, topY)
                path.lineTo(cx + foreheadW, topY)
                path.lineTo(cx + cheekW, cy)
                path.lineTo(cx + chinW, chinY)
                path.lineTo(cx - chinW, chinY)
                path.lineTo(cx - cheekW, cy)
                path.close()
                drawPath(path = path, color = guideColor, style = strokeStyle)
            }

            FaceShape.OBLONG -> {
                val rx = w * 0.30f
                val ry = h * 0.42f
                drawOval(
                    color = guideColor,
                    topLeft = Offset(cx - rx, cy - ry),
                    size = Size(rx * 2, ry * 2),
                    style = strokeStyle
                )
            }
        }

        // Facial Guide Landmark Crosshairs
        drawLine(
            color = guideColor.copy(alpha = 0.4f),
            start = Offset(cx - 40f, cy - h * 0.12f),
            end = Offset(cx + 40f, cy - h * 0.12f),
            strokeWidth = 1.5f
        )
        drawLine(
            color = guideColor.copy(alpha = 0.4f),
            start = Offset(cx - 30f, cy + h * 0.12f),
            end = Offset(cx + 30f, cy + h * 0.12f),
            strokeWidth = 1.5f
        )
    }
}

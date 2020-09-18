package com.kotlin.zmvvm.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by zhgq on 2020/9/18
 * Describe：
 * @author 13718
 */
class DimpleView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    /**
     * 存放粒子的集合
     */
    private var particles = mutableListOf<Particle>()

    /**
     * 画笔
     */
    private val paint = Paint()

    private var random=Random()
    private var path=Path()
    private val pathMeasure = PathMeasure()
    private var pos = FloatArray(2)
    private val tan = FloatArray(2)



    private var animator=ValueAnimator.ofFloat(0f,1f)

    private  var centerX =0f

    private var centerY=0f


    init {
        animator.duration=2000
        animator.repeatCount=-1
        animator.interpolator=LinearInterpolator()
        animator.addUpdateListener {
            updateParticle(it.animatedValue as Float)
            invalidate()
        }
    }


    private fun updateParticle(value : Float){
        particles.forEach {particle->
            if(particle.offset >particle.maxOffset){
                particle.offset=0f
                particle.speed= (random.nextInt(10)+5).toFloat()
            }
            particle.alpha= ((1f - particle.offset / particle.maxOffset)  * 225f).toInt()
            particle.x = (centerX+ cos(particle.angle) * (280f + particle.offset)).toFloat()
            if (particle.y > centerY) {
                particle.y = (sin(particle.angle) * (280f + particle.offset) + centerY).toFloat()
            } else {
                particle.y = (centerY - sin(particle.angle) * (280f + particle.offset)).toFloat()
            }
            particle.offset += particle.speed.toInt()
        }



    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        path.addCircle(centerX, centerY, 280f, Path.Direction.CCW)
        pathMeasure.setPath(path, false)
        var nextX = 0f
        var speed=0f
        var nextY=0f
        var angle=0.0
        var offSet=0
        var maxOffset=0
        for (i in 0..2000) {
            pathMeasure.getPosTan(i / 2000f * pathMeasure.length, pos, tan)
            nextX = pos[0]+random.nextInt(6) - 3f
            nextY=  pos[1]+random.nextInt(6) - 3f
            angle=acos(((pos[0] - centerX) / 280f).toDouble())
            speed= random.nextInt(2) + 2f
            offSet = random.nextInt(200)
            maxOffset = random.nextInt(200)
            particles.add(
                Particle(nextX, nextY, 2f, speed, 100,offSet.toFloat(),angle, maxOffset.toFloat())
            )
        }
        animator.start()

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
        paint.isAntiAlias = true

        particles.forEach {
            paint.alpha=it.alpha
            canvas?.drawCircle(it.x,it.y,it.radius,paint)
        }

    }

}
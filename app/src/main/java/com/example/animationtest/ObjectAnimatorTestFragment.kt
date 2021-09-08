package com.example.animationtest

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.animationtest.databinding.FrgamentObjectAnimatorTestBinding

private const val REPEAT_COUNT = 1
private const val DURATION = 500L
private const val START_ROTATION_ANGLE = -360f
private const val END_ROTATION_ANGLE = 0f
private const val MOVE_DISTANCE = 200f
private const val SCALE_MODIFIER = 4f
private const val MIN_ALPHA = 0f
private const val TINT_PROPERTY_NAME = "colorFilter"

class ObjectAnimatorTestFragment : Fragment(R.layout.frgament_object_animator_test) {
    private val binding: FrgamentObjectAnimatorTestBinding by viewBinding(
        FrgamentObjectAnimatorTestBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rotateButton.setOnClickListener(::rotate)
        binding.moveButton.setOnClickListener(::move)
        binding.scaleButton.setOnClickListener(::scale)
        binding.fadeButton.setOnClickListener(::fade)
        binding.changeColorButton.setOnClickListener(::changeColor)
    }

    private fun rotate(view: View) {
        ObjectAnimator.ofFloat(
            binding.starImageView,
            View.ROTATION,
            START_ROTATION_ANGLE,
            END_ROTATION_ANGLE
        ).apply {
            duration = DURATION
            disableViewDuringAnimation(view)
            start()
        }
    }

    private fun move(view: View) {
        ObjectAnimator.ofFloat(binding.starImageView, View.TRANSLATION_X, MOVE_DISTANCE).apply {
            repeatCount = REPEAT_COUNT
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }

    private fun scale(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, SCALE_MODIFIER)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, SCALE_MODIFIER)
        ObjectAnimator.ofPropertyValuesHolder(binding.starImageView, scaleX, scaleY).apply {
            repeatCount = REPEAT_COUNT
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }

    private fun fade(view: View) {
        ObjectAnimator.ofFloat(binding.starImageView, View.ALPHA, MIN_ALPHA).apply {
            repeatCount = REPEAT_COUNT
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }

    private fun changeColor(view: View) {
        ObjectAnimator.ofArgb(binding.starImageView, TINT_PROPERTY_NAME, Color.YELLOW, Color.RED)
            .apply {
                duration = DURATION
                repeatCount = REPEAT_COUNT
                repeatMode = ObjectAnimator.REVERSE
                disableViewDuringAnimation(view)
                start()
            }
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        doOnStart {
            view.isEnabled = false
        }

        doOnEnd {
            view.isEnabled = true
        }
    }
}
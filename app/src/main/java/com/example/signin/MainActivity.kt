package com.example.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.widget.doOnTextChanged
import com.example.extensions.isEmail
import com.example.signin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        init()
    }

    private fun setListeners() {
        binding.emailEditTxtView.doOnTextChanged { text, _, _, _ ->
            if (!text.toString().isEmail())
                binding.emailEditTxtView.error = getString(R.string.invalid_mail)
        }

        // Better way to replace by com.google.android.material.textfield.TextInputLayout
        binding.eyeImgView.setOnClickListener {
            if (binding.passwordEditTxtView.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                binding.eyeImgView.setImageResource(R.drawable.ic_eye_open)
                binding.passwordEditTxtView.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.eyeImgView.setImageResource(R.drawable.ic_eye_close)
                binding.passwordEditTxtView.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun init() {
        binding.shareTxtView.text = buildSpannedString {
            bold {
                inSpans(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            binding.shareTxtView.context,
                            R.color.black
                        )
                    )
                ) {
                    append(getString(R.string.share))
                }
                inSpans(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            binding.shareTxtView.context,
                            R.color.main_color
                        )
                    )
                ) {
                    append(getString(R.string.mac))
                }
            }
        }
    }
}
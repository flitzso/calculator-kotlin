package com.flitzso.calculator_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.flitzso.calculator_kotlin.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Calculator"

        binding.tvOne.setOnClickListener { appendOnExpression("1", true) }
        binding.tvTwo.setOnClickListener { appendOnExpression("2", true) }
        binding.tvThree.setOnClickListener { appendOnExpression("3", true) }
        binding.tvFour.setOnClickListener { appendOnExpression("4", true) }
        binding.tvFive.setOnClickListener { appendOnExpression("5", true) }
        binding.tvSix.setOnClickListener { appendOnExpression("6", true) }
        binding.tvSeven.setOnClickListener { appendOnExpression("7", true) }
        binding.tvEight.setOnClickListener { appendOnExpression("8", true) }
        binding.tvNine.setOnClickListener { appendOnExpression("9", true) }
        binding.tvZero.setOnClickListener { appendOnExpression("0", true) }
        binding.tvDot.setOnClickListener { appendOnExpression(".", true) }

        binding.tvPlus.setOnClickListener { appendOnExpression("+", false) }
        binding.tvMin.setOnClickListener { appendOnExpression("-", false) }
        binding.tvMult.setOnClickListener { appendOnExpression("*", false) }
        binding.tvOpen.setOnClickListener { appendOnExpression("(", false) }
        binding.tvClose.setOnClickListener { appendOnExpression(")", false) }
        binding.tvDivide.setOnClickListener { appendOnExpression("/", false) }

        binding.tvClear.setOnClickListener {
            binding.tvExpression.text = ""
            binding.tvResult.text = ""
        }

        binding.tvBack.setOnClickListener {
            val string = binding.tvExpression.text.toString()
            if (string.isNotEmpty()) {
                binding.tvExpression.text = string.substring(0, string.length - 1)
            }
            binding.tvResult.text = ""
        }

        binding.tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    binding.tvResult.text = longResult.toString()
                else
                    binding.tvResult.text = longResult.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if (binding.tvResult.text.isNotEmpty()) {
            binding.tvExpression.text = ""
        }

        if (canClear) {
            binding.tvResult.text = ""
            binding.tvExpression.append(string)
        } else {
            binding.tvExpression.append(binding.tvResult.text)
            binding.tvExpression.append(string)
            binding.tvResult.text = ""
        }
    }
}

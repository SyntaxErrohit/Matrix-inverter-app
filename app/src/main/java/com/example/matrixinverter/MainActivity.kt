package com.example.matrixinverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val generate = findViewById<Button>(R.id.button)
        val result = findViewById<TextView>(R.id.result)
        val resultd = findViewById<TextView>(R.id.resultd)
        val m00 = findViewById<EditText>(R.id.box00)
        val m01 = findViewById<EditText>(R.id.box01)
        val m02 = findViewById<EditText>(R.id.box02)
        val m10 = findViewById<EditText>(R.id.box10)
        val m11 = findViewById<EditText>(R.id.box11)
        val m12 = findViewById<EditText>(R.id.box12)
        val m20 = findViewById<EditText>(R.id.box20)
        val m21 = findViewById<EditText>(R.id.box21)
        val m22 = findViewById<EditText>(R.id.box22)

        generate.setOnClickListener {
            val matrix = arrayOf(IntArray(3), IntArray(3), IntArray(3))
            for (i in 0..2) {
                for (j in 0..2) {
                    matrix[i][j] = when (i.toString()+j.toString()) {
                        "00" -> m00.text.toString().toInt()
                        "01" -> m01.text.toString().toInt()
                        "02" -> m02.text.toString().toInt()
                        "10" -> m10.text.toString().toInt()
                        "11" -> m11.text.toString().toInt()
                        "12" -> m12.text.toString().toInt()
                        "20" -> m20.text.toString().toInt()
                        "21" -> m21.text.toString().toInt()
                        "22" -> m22.text.toString().toInt()
                        else -> 0
                    }
                }
            }
            val adj = Array(3) { IntArray(3) }
            var sign = 1

            for (imain in 0..2) {
                for (jmain in 0..2) {
                    val l = mutableListOf<Int>()
                    for (i in 0..2) {
                        for (j in 0..2) {
                            if (i != imain && j != jmain) {
                                l.add(matrix[i][j])
                            }
                        }
                    }
                    adj[jmain][imain] = d2(l)*sign
                    sign *= -1
                }
            }

            val d = (0..2).sumOf { adj[it][0] * matrix[0][it] }

            if (d != 0) {
                val textd = "Result:\n1\n" + "-".repeat(d.toString().length+2) + "\n" + d.toString()
                var s = "\n"
                for (i in adj) {
                    s += "|${i[0]} ${i[1]} ${i[2]}|\n"
                }
                resultd.text = textd
                result.text = s
            } else {
                Toast.makeText(this, "Matrix is singular", Toast.LENGTH_SHORT).show()
                val textd = "None."
                val textm = "Matrix is singular"
                resultd.text = textd
                result.text = textm
            }

        }
    }
}

fun d2 (m: MutableList<Int>):Int {
    return (m[0]*m[3]) - (m[1]*m[2])
}
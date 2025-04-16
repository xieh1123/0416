package com.example.s16_m

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton =findViewById<Button>(R.id.backbut)
        backButton.setOnClickListener(
            {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        )
        val result =findViewById<TextView>(R.id.total)
        val winner =findViewById<TextView>(R.id.Winner)
        val getwin =intent.getStringExtra("winner")
        var win :Int =intent.getIntExtra("win",0)
        var lose:Int =intent.getIntExtra("lose",0)
        var nowin =intent.getIntExtra("nowin",0)
        if (getwin=="電腦"){
            result.text ="戰績\n 勝:$lose 敗:$win 平:$nowin"
        }
        else{
            result.text ="戰績\n 勝:$win 敗:$lose 平:$nowin"
        }
        winner.text="勝利者\n$getwin"




    }
}
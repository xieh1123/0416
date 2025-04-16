package com.example.s16_m

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val player_name = findViewById<EditText>(R.id.playerName)
        val choose = findViewById<TextView>(R.id.text_1)
        val Text_choose = findViewById<TextView>(R.id.Text_chosse)
        val Name = findViewById<TextView>(R.id.name)
        val player_choose = findViewById<TextView>(R.id.playerChoose)
        val pcChoose = findViewById<TextView>(R.id.pcChoose)
        val winner = findViewById<TextView>(R.id.winner)
        val total = findViewById<TextView>(R.id.total)
        val result = findViewById<Button>(R.id.result)
        val play = findViewById<Button>(R.id.button_play)
        val radioGroup = findViewById<RadioGroup>(R.id.R)
        var win = 0
        var lose = 0
        var nowin = 0
        val listview = findViewById<ListView>(R.id.List)
        var array = ArrayList<String>()
        total.text = "戰績\n" + " 勝:$win 敗:$lose 平:$nowin"

        var log = 10
        Log.d("Activity", "已觸發顯示log")
        Log.d("Activity", log.toString())

        AlertDialog.Builder(this)
            .setTitle("遊戲開始")
            .setMessage("歡迎來到剪刀石頭布遊戲!\n請輸入你的名字並選擇出拳")
            .setPositiveButton("開始遊戲", null)
            .show()

        play.setOnClickListener {
            val Player_name = player_name.text.toString().trim()
            if (Player_name.isEmpty()) {
                Toast.makeText(this, "請輸入玩家姓名", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val selectedOption = radioGroup.checkedRadioButtonId
            if (selectedOption == -1) {
                Toast.makeText(this, "請選擇剪刀、石頭或布", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val playChoice = when (selectedOption) {
                R.id.s -> "剪刀"
                R.id.rook -> "石頭"
                R.id.paper -> "布"
                else -> ""
            }
            val choice = listOf("剪刀", "石頭", "布")
            val targetmora = choice.random()

            val resultText = when {
                playChoice == targetmora -> {
                    nowin++
                    "平手"
                }

                (playChoice == "剪刀" && targetmora == "布") ||
                        (playChoice == "石頭" && targetmora == "剪刀") ||
                        (playChoice == "布" && targetmora == "石頭") -> {
                    win++
                    "$Player_name 獲勝！"
                }

                else -> {
                    lose++
                    "電腦勝利"
                }
            }
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nowin", nowin)
            intent.putExtra("win", win)
            intent.putExtra("lose", lose)
            if (win == 4) {
                AlertDialog.Builder(this)
                    .setTitle("遊戲結束")
                    .setMessage("恭喜$Player_name 獲得最終勝利!\n總計:勝:$win 敗:$lose 平:$nowin")
                    .setPositiveButton("查看結果", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            intent.putExtra("winner", Player_name)
                            startActivity(intent)
                            finish()
                        }
                    })
                    .show()

            } else if (lose == 4) {
                AlertDialog.Builder(this)
                    .setTitle("遊戲結束")
                    .setMessage("恭喜 電腦 獲得最終勝利!\n總計:勝:$win 敗:$lose 平:$nowin")
                    .setPositiveButton("查看結果", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            intent.putExtra("winner", "電腦")
                            startActivity(intent)
                            finish()
                        }
                    })
                    .show()

            }
            var win_time = win / (win + lose).toFloat()
            win_time=win_time*100
            total.text = "勝:$win 敗:$lose 平:$nowin\n勝率$win_time %"
            Name.text = "名字:$Player_name"
            player_choose.text = "我方猜:$playChoice"
            pcChoose.text = "電腦猜:$targetmora"
            winner.text = "勝利者:$resultText"
            array.add("勝:$win 敗:$lose 平:$nowin\n勝率$win_time")
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, array
            )
            listview.adapter = adapter



        }
        result.setOnClickListener {
            win = 0
            lose = 0
            nowin = 0
            total.text = "戰績\n" + " 勝:$win 敗:$lose 平:$nowin"
        }
    }
}

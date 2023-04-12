package br.edu.ifrs.poa.a4coffe.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.edu.ifrs.poa.a4coffe.R

class IntroActivity : AppCompatActivity(R.layout.activity_intro) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainButton = findViewById<Button>(R.id.activity_intro_button)

        mainButton.setOnClickListener {
            val intent = Intent(this,ListCoffeeActivity::class.java)
            startActivity(intent)
        }

    }
}
package br.edu.ifrs.poa.a4coffe.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifrs.poa.a4coffe.R
import br.edu.ifrs.poa.a4coffe.database.CoffeeDatabase
import br.edu.ifrs.poa.a4coffe.database.DatabaseBuilder
import br.edu.ifrs.poa.a4coffe.model.Coffee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddCoffeeForm : AppCompatActivity() {

    private var selectedItem: String? = null
    private lateinit var db: CoffeeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coffee_form)
        db = DatabaseBuilder.getDatabase(this)
        configTheDropDownMenu()
        configCoffeeButton()
    }

    private fun configTheDropDownMenu() {
        val items = listOf("Fraca", "Média", "Forte")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        val dropdownMenu =
            findViewById<AutoCompleteTextView>(R.id.activity_add_coffee_edit_text_intensity)
        val button = findViewById<Button>(R.id.activity_add_coffee_form_save_button)

        dropdownMenu.setAdapter(adapter)
        dropdownMenu.threshold = 100
        dropdownMenu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedItem =
                    if (position != AdapterView.INVALID_POSITION) parent.getItemAtPosition(position) as String else "Média"
                button.isEnabled = true
            }
    }

    private fun configCoffeeButton() {
        var button = findViewById<Button>(R.id.activity_add_coffee_form_save_button)
        button.isEnabled = false
        button.setOnClickListener {
            val coffee = createCoffee()

            if (coffee.name.isBlank() && coffee.grain.isBlank()) {
                Toast.makeText(
                    this,
                    "O Nome e o tipo do grão não podem ficar em branco!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {

                println("This is coffee was been saved: $coffee")

                button.isEnabled = false
                CoroutineScope(Dispatchers.IO).launch {
                    val deferredInsert = async { db.coffeeDao().insert(coffee) }
                    deferredInsert.await()
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }

    private fun createCoffee(): Coffee {
        val coffeeName = findViewById<EditText>(R.id.activity_add_coffee_edit_text_name)
        val grain = findViewById<EditText>(R.id.activity_add_coffee_edit_text_grain)
        val goodWith = findViewById<EditText>(R.id.activity_add_coffee_edit_text_gets_good_with)

        return Coffee(
            name = coffeeName.text.toString(),
            grain = grain.text.toString(),
            goodWith = goodWith.text.toString(),
            intensity = selectedItem.toString()
        )
    }
}
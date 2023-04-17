package br.edu.ifrs.poa.a4coffe.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifrs.poa.a4coffe.R
import br.edu.ifrs.poa.a4coffe.database.CoffeeDatabase
import br.edu.ifrs.poa.a4coffe.database.DatabaseBuilder
import br.edu.ifrs.poa.a4coffe.model.Coffee
import br.edu.ifrs.poa.a4coffe.ui.recyclerview.adapter.ListCoffeeAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListCoffeeActivity : AppCompatActivity() {

    private lateinit var db: CoffeeDatabase
    private lateinit var coffees: List<Coffee>
    private lateinit var coffeeRecyclerView: RecyclerView
    private lateinit var coffeeAdapter: ListCoffeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_coffee)

        db = DatabaseBuilder.getDatabase(this)

        coffeeRecyclerView = findViewById(R.id.recycler_view) // Initialize the RecyclerView

        val coffeAd = configRecyclerView()
        coffeeAdapter = coffeAd

        loadNotes()

        configAddButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_COFFEE && resultCode == Activity.RESULT_OK) {
            loadNotes()
        }
    }

    private fun configRecyclerView(): ListCoffeeAdapter {
        coffeeRecyclerView.layoutManager = LinearLayoutManager(this)
        val coffeeAdapter = ListCoffeeAdapter(this, listOf())
        coffeeRecyclerView.adapter = coffeeAdapter
        return coffeeAdapter
    }

    private fun loadNotes() {
        CoroutineScope(Dispatchers.IO).launch {
            val coffeeDao = db.coffeeDao()
            val allCoffees = coffeeDao.getAllCoffees()
            withContext(Dispatchers.Main) {
                coffees = allCoffees
                coffeeAdapter.setData(coffees)
            }
        }
    }

    private fun configAddButton() {
        val addCoffeeButton = findViewById<FloatingActionButton>(R.id.fab_add_coffee)
        addCoffeeButton.setOnClickListener {
            val intent = Intent(this, AddCoffeeForm::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_COFFEE)
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_COFFEE = 1
    }
}

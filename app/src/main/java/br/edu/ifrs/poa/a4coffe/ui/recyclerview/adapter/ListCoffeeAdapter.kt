package br.edu.ifrs.poa.a4coffe.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifrs.poa.a4coffe.R
import br.edu.ifrs.poa.a4coffe.model.Coffee

class ListCoffeeAdapter(
    private val context: Context,
    private var coffees: List<Coffee>
) : RecyclerView.Adapter<ListCoffeeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.coffee_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coffee = coffees[position]
        holder.bind(coffee)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private val grainTextView: TextView = itemView.findViewById(R.id.grain_text_view)
        private val goodWithTextView: TextView = itemView.findViewById(R.id.good_with_text_view)
        private val intensityTextView: TextView = itemView.findViewById(R.id.intensity_text_view)
        fun bind(coffee: Coffee) {
            nameTextView.text = coffee.name
            grainTextView.text = coffee.grain
            goodWithTextView.text = coffee.goodWith
            intensityTextView.text = coffee.intensity
        }
    }

    override fun getItemCount(): Int = coffees.size

    fun setData(newData: List<Coffee>) {
        this.coffees = newData
        notifyDataSetChanged()
    }
}

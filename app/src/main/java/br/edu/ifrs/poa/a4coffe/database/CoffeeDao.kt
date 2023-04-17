package br.edu.ifrs.poa.a4coffe.database

import androidx.room.*
import br.edu.ifrs.poa.a4coffe.model.Coffee

@Dao
interface CoffeeDao {

    @Query("SELECT * FROM coffees")
    fun getAllCoffees(): List<Coffee>

    @Insert
    fun insert(coffee:Coffee)

    @Update
    fun update(coffee:Coffee)

    @Delete
    fun delete(coffee:Coffee)
}

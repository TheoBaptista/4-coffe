package br.edu.ifrs.poa.a4coffe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifrs.poa.a4coffe.model.Coffee

@Database(entities = [Coffee::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao
}
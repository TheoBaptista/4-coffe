package br.edu.ifrs.poa.a4coffe.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    @Volatile
    private var INSTANCE: CoffeeDatabase? = null

    fun getDatabase(context: Context): CoffeeDatabase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CoffeeDatabase::class.java,
                "coffees"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}
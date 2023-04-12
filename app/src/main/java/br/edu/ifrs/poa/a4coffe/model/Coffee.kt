package br.edu.ifrs.poa.a4coffe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffees")
data class Coffee(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    )

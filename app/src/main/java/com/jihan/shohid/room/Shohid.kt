package com.jihan.shohid.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "martyr_information")
data class Shohid(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val age: String,
    val birth_place: String,
    val date_of_death: String,
    val description: String,
    val dob: String,
    val en_age: String,
    val en_birth_place: String,
    val en_date_of_death: String,
    val en_description: String,
    val en_dob: String,
    val en_name: String,
    val en_occupation: String,
    val en_personal_life: String,
    val en_reason: String,
    val name: String,
    val occupation: String,
    val personal_life: String,
    val reason: String
) : Serializable

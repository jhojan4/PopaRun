package edu.unicauca.example.poparun.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class user (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val country: String,
    val phoneNumber: String,
    val password: String,
    val confirmPassword: String
)

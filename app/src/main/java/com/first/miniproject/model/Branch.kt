package com.first.miniproject.model

enum class BranchType(val displayName: String) {
    MAIN("Main"), ATM("ATM"), SERVICE("Service"), OTHER("Other")
}

data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String,
    val phone: String,
    val hours: String,
    val locationUrl: String,
    val imageUri: String? = null
)
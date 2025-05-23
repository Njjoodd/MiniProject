package com.first.miniproject.model

enum class BranchType(val displayName: String, val priority: Int) {
    MAIN("Main", 1),
    ATM("ATM", 2),
    SERVICE("Service", 3),
    OTHER("Other", 4)
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
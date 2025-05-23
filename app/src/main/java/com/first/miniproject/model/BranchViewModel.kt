package com.first.miniproject.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.first.miniproject.model.Branch
import com.first.miniproject.model.BranchType

class BranchViewModel : ViewModel() {
    var branches by mutableStateOf(
        listOf(
            Branch(
                id = 1,
                name = "Qortuba Branch",
                type = BranchType.MAIN,
                address = "Block 5, Qortuba, Kuwait",
                phone = "99990000",
                hours = "8AM - 5PM",
                locationUrl = "https://maps.google.com"
            ),
            Branch(
                id = 2,
                name = "Bayan Branch",
                type = BranchType.ATM,
                address = "Block 2, Bayan",
                phone = "99900000",
                hours = "Open 24/7",
                locationUrl = "https://maps.google.com"
            )
        )
    )
        private set

    var favoriteBranchId by mutableStateOf<Int?>(null)

        private set

    fun setFavorite(branchId: Int) {
        favoriteBranchId = if (favoriteBranchId == branchId) null else branchId
    }

    fun isFavorite(branchId: Int): Boolean {
        return branchId == favoriteBranchId
    }



    fun getBranchById(id: Int): Branch? {
        return branches.find { it.id == id }
    }
}

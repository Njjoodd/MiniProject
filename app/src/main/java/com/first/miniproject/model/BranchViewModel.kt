package com.first.miniproject.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.first.miniproject.repository.BranchRepository

class BranchViewModel : ViewModel() {
    var branches by mutableStateOf(BranchRepository.dummyBranches)
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
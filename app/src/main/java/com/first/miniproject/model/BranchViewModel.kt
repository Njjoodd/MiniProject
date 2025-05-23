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

    var isSortedByName by mutableStateOf(false)
        private set

    var isSortedByType by mutableStateOf(false)
        private set

    var isFiltered by mutableStateOf(false)
        private set

    fun sortBranchesByName() {
        branches = branches.sortedBy { it.name }
        isFiltered = true
    }

    fun sortBranchesByType() {
        branches = branches.sortedBy { it.type.name }
        isFiltered = true
    }


    fun filterBranchesByHours() {
        branches = BranchRepository.dummyBranches.filter {
            it.hours.contains("24/7", ignoreCase = true)
        }
        isFiltered = true
        isSortedByName = false
        isSortedByType = false
    }

    fun resetBranches() {
        branches = BranchRepository.dummyBranches
        isFiltered = false
    }

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

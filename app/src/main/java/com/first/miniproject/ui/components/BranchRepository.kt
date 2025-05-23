package com.first.miniproject.ui.components

import com.first.miniproject.model.Branch
import com.first.miniproject.model.BranchType

object BranchRepository {
    val dummyBranches = listOf(
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
}

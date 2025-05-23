package com.first.miniproject.repository

import com.first.miniproject.model.Branch
import com.first.miniproject.model.BranchType

object BranchRepository {
    val dummyBranches = listOf(
        Branch(
            id = 1,
            name = "Main HQ Branch",
            type = BranchType.MAIN,
            address = "Downtown District, Kuwait City",
            phone = "+965 00000000",
            hours = "8AM - 5PM",
            locationUrl = "https://maps.google.com",
           imageUri = "https://picsum.photos/id/1025/600/300"

            //"https://plus.unsplash.com/premium_vector-1724738558612-56d27145e1de?w=900&auto=format&fit=crop&q=60"
        ),
        Branch(
            id = 2,
            name = "Small Branch 1",
            type = BranchType.ATM,
            address = "Block 2, somewhere",
            phone = "+965 00000001",
            hours = "Open 24/7",
            locationUrl = "https://maps.google.com",
            imageUri = "https://plus.unsplash.com/premium_vector-1724832743818-0f8a81abbc9d?w=900&auto=format&fit=crop&q=60"
        ),
        Branch(
            id = 3,
            name = "Service Branch 1",
            type = BranchType.SERVICE,
            address = "Main St, somewhere",
            phone = "+965 00000002",
            hours = "8AM - 3PM",
            locationUrl = "https://maps.google.com",
            imageUri = "https://plus.unsplash.com/premium_vector-1724597503013-b79c34916aee?w=900&auto=format&fit=crop&q=60"
        ),
        Branch(
            id = 4,
            name = "Service Branch 2",
            type = BranchType.SERVICE,
            address = "Block 4, somewhere",
            phone = "+965 00000003",
            hours = "8AM - 3PM",
            locationUrl = "https://maps.google.com",
            imageUri = "https://plus.unsplash.com/premium_vector-1724832743900-af626d403526?w=900&auto=format&fit=crop&q=60"
        ),
        Branch(
            id = 5,
            name = "Small Branch 2",
            type = BranchType.ATM,
            address = "Block 6, somewhere",
            phone = "+965 00000004",
            hours = "Open 24/7",
            locationUrl = "https://maps.google.com",
            imageUri = "https://plus.unsplash.com/premium_vector-1721716189903-b36d5bd6f42b?w=900&auto=format&fit=crop&q=60"
        ),
        Branch(
            id = 6,
            name = "Some Branch",
            type = BranchType.OTHER,
            address = "Area 51, somewhere",
            phone = "+965 00000005",
            hours = "8AM - 8PM",
            locationUrl = "https://maps.google.com",
            imageUri = "https://plus.unsplash.com/premium_vector-1724310048248-d6b52e189969?w=900&auto=format&fit=crop&q=60"
        )
    )

}

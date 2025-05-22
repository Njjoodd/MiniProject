package com.first.miniproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.first.miniproject.model.Branch

@Composable
fun BranchesList(
    branches: List<Branch>,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(branches) { branch ->
            BranchCard(branch = branch, onClick = { onItemClicked(branch.id) })
        }
    }
}

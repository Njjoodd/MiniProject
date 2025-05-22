package com.first.miniproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.first.miniproject.R
import com.first.miniproject.model.Branch

@Composable
fun BranchDetails(branch: Branch, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val image = branch.imageUri ?: R.drawable.placeholder_branch

        Image(
            painter = painterResource(id = image),
            contentDescription = "Branch Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(text = branch.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Type: ${branch.type.displayName}")
        Text(text = "Address: ${branch.address}")
        Text(text = "Phone: ${branch.phone}")
        Text(text = "Hours: ${branch.hours}")

        Button(
            onClick = { uriHandler.openUri(branch.locationUrl) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Open in Google Maps")
        }
    }
}

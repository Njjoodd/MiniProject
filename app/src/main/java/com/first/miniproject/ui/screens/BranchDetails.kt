package com.first.miniproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.first.miniproject.R
import com.first.miniproject.model.Branch
import com.first.miniproject.model.BranchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetails(
    branch: Branch,
    navController: NavController,
    viewModel: BranchViewModel,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = { Text(branch.name) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = branch.imageUri,
            contentDescription = "Branch Image",
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            placeholder = painterResource(id = R.drawable.placeholder_branch),
            error = painterResource(id = R.drawable.placeholder_branch)
        )



        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Type: ${branch.type.displayName}", fontSize = 16.sp)
        Text(text = "Address: ${branch.address}", fontSize = 16.sp)
        Text(text = "Phone: ${branch.phone}", fontSize = 16.sp)
        Text(text = "Hours: ${branch.hours}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { uriHandler.openUri(branch.locationUrl) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Open in Google Maps")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.setFavorite(branch.id) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = if (viewModel.isFavorite(branch.id)) "Remove Favorite 🌟" else "Add to Favorites"
            )
        }
    }
}
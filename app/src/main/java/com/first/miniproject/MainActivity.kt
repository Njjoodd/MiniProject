package com.first.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.first.miniproject.model.BranchViewModel
import com.first.miniproject.ui.components.BranchesList
import com.first.miniproject.ui.components.SearchBar
import com.first.miniproject.ui.screens.BranchDetails
import com.first.miniproject.ui.theme.MiniProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTheme {
                val viewModel: BranchViewModel = viewModel()
                val navController = rememberNavController()
                var filterMenuExpanded by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Bank Branches",
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            actions = {
                                if (viewModel.isFiltered) {
                                    TextButton(onClick = { viewModel.resetBranches() }) {
                                        Text(
                                            "Reset Filters",
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }
                                IconButton(onClick = { filterMenuExpanded = true }) {
                                    Icon(
                                        imageVector = Icons.Default.FilterList,
                                        contentDescription = "Filter",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                DropdownMenu(
                                    expanded = filterMenuExpanded,
                                    onDismissRequest = { filterMenuExpanded = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Sort by Name") },
                                        onClick = {
                                            viewModel.sortBranchesByName()
                                            filterMenuExpanded = false
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Sort by Type") },
                                        onClick = {
                                            viewModel.sortBranchesByType()
                                            filterMenuExpanded = false
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Open 24/7") },
                                        onClick = {
                                            viewModel.filterBranchesByHours()
                                            filterMenuExpanded = false
                                        }
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "branchList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("branchList") {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                SearchBar(
                                    query = viewModel.searchQuery,
                                    onQueryChange = { viewModel.updateSearchQuery(it) }
                                )
                                BranchesList(
                                    branches = viewModel.filteredBranches,
                                    viewModel = viewModel,
                                    onItemClicked = { branchId ->
                                        navController.navigate("branchDetails/$branchId")
                                    }
                                )
                            }
                        }
                        composable("branchDetails/{branchId}") { backStackEntry ->
                            val branchId = backStackEntry.arguments?.getString("branchId")?.toIntOrNull()
                            val branch = viewModel.getBranchById(branchId ?: -1)
                            branch?.let {
                                BranchDetails(branch = it, navController = navController, viewModel = viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

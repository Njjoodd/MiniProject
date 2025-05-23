package com.first.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Sort
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
                    topBar = {
                        TopAppBar(
                            title = { Text("Bank Branches") },
                            actions = {
                                if (viewModel.isFiltered) {
                                    TextButton(onClick = { viewModel.resetBranches() }) {
                                        Text("Reset Filters")
                                    }
                                }
                                IconButton(onClick = { filterMenuExpanded = true }) {
                                    Icon(Icons.Default.FilterList, contentDescription = "Filter")
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
                                        text = { Text("Show 24/7") },
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
                            Column(modifier = Modifier.padding(16.dp)) {
                                SearchBar(
                                    query = viewModel.searchQuery,
                                    onQueryChange = { viewModel.updateSearchQuery(it) }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
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

package com.first.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.first.miniproject.model.BranchViewModel
import com.first.miniproject.ui.components.BranchesList
import com.first.miniproject.ui.screens.BranchDetails
import com.first.miniproject.ui.theme.MiniProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTheme {
                val viewModel: BranchViewModel = viewModel()
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "branchList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("branchList") {
                            BranchesList(
                                branches = viewModel.branches,
                                viewModel = viewModel,
                                onItemClicked = { branchId ->
                                    navController.navigate("branchDetails/$branchId")
                                }
                            )
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
package com.first.miniproject

import com.first.miniproject.ui.components.BranchesList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.first.miniproject.model.Branch
import com.first.miniproject.model.BranchType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.first.miniproject.ui.components.BranchCard
import com.first.miniproject.ui.screens.BranchDetails
import com.first.miniproject.ui.theme.MiniProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "branchList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("branchList") {
                            BranchesList(
                                branches = dummyBranches,
                                onItemClicked = { branchId ->
                                    navController.navigate("branchDetails/$branchId")
                                }
                            )
                        }

                        composable("branchDetails/{branchId}") { backStackEntry ->
                            val branchId =
                                backStackEntry.arguments?.getString("branchId")?.toIntOrNull()
                            val branch = dummyBranches.find { it.id == branchId }

                            branch?.let {
                                BranchDetails(branch = it)
                            }
                        }
                    }
                }
            }
        }
    }
}
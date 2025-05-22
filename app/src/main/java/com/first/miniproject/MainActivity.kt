package com.first.miniproject

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.first.miniproject.ui.theme.MiniProjectTheme

enum class BranchType(val displayName: String) {
    MAIN("Main"),
    ATM("ATM"),
    SERVICE("Service"),
    OTHER("Other")
}
data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String,
    val phone: String,
    val hours: String,
    val locationUrl: String,
    val imageUri: Int? = null
)

val branchesList = listOf(
    Branch(
        id = 1,
        name = "Qortuba Branch",
        type = BranchType.MAIN,
        address = "Block 5, Qortuba, Kuwait",
        phone = "99990000",
        hours = "8AM - 5PM",
        locationUrl = "https://maps.google.com",
        imageUri = null
    ),
    Branch(
        id = 2,
        name = "Bayan Branch",
        type = BranchType.ATM,
        address = "Block 2, Bayan",
        phone = "00009999",
        hours = "Open 24/7",
        locationUrl = "https://maps.google.com",
        imageUri = null
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // BranchesList(
//                        branches = branchesList,
//                        modifier = Modifier.padding(innerPadding)
                        BranchDetails(
                            branch = branchesList[0],
                            modifier = Modifier.padding(innerPadding)
                        )

                  //  )
                }
            }
        }
    }
}

@Composable
fun BranchesList(branches: List<Branch>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(branches) { branch ->
            BranchCard(branch = branch)
        }
    }
}

@Composable
fun BranchCard(branch: Branch, modifier: Modifier = Modifier) {
    androidx.compose.material3.Card(
        modifier = modifier
            .fillMaxSize()

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = branch.name)
            Text(text = branch.address)
        }
    }
}

@Composable
fun BranchDetails(branch: Branch, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Show image or fallback placeholder
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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiniProjectTheme {

    }
}

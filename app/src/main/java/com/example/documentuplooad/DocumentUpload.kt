import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DocumentUploadScreen() {
    var selectedDocumentUri by remember { mutableStateOf<Uri?>(null) }

    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedDocumentUri = uri
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                documentLauncher.launch("application/pdf") // You can specify the MIME type of the document here
            }
        ) {
            Text("Select Document")
        }

        selectedDocumentUri?.let { uri ->
            Text("Selected Document: ${uri.lastPathSegment}")

            // Here, you can handle the selected document as needed (e.g., read content, upload, etc.).
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        DocumentUploadScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}

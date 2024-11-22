package inoxoft.simon.matwana.view.pages.Auth



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import inoxoft.simon.matwana.viewmodel.UserViewModel


@Composable
fun AuthObserver(navController: NavHostController, viewModel: UserViewModel) {
    // Observe authState from the ViewModel via stateflow
    val authState by viewModel.authState.collectAsState()

    // Navigate to the appropriate screen based on the authState

}


@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "Loading, please wait...")
    }
}
@Composable
fun ErrorScreen(x0: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicText(text = "Error: $x0", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { /* Content action invoked here */ }) {
            Text(text = "Retry")
        }
        Spacer(modifier = Modifier.height(16.dp))
        content()
    }
}

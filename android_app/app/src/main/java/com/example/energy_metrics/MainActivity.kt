package com.example.energy_metrics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.energy_metrics.ui.theme.Energy_metricsTheme

class MainActivity : ComponentActivity() {
    var context = this;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Energy_metricsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    EnergyReading(context)
                }
            }
        }

    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun EnergyReading(context: MainActivity) {
    var e = EnergyProfiler(context)
    e.readEnergy()
    Text("Energy delta is :" + e.energy_delta)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Energy_metricsTheme {
        Greeting("Android")
    }
}
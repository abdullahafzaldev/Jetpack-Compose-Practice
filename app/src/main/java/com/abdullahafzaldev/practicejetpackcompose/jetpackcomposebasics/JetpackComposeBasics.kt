package com.abdullahafzaldev.practicejetpackcompose.jetpackcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullahafzaldev.practicejetpackcompose.ui.theme.PracticeJetPackComposeTheme

class JetpackComposeBasics : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeJetPackComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){

    var shouldShowOnBoarding by rememberSaveable  { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = {
            shouldShowOnBoarding = false
        })
    } else {
        GreetingsList()
    }

}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    androidx.compose.material.Surface() {
        Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingScreenPreview() {
    PracticeJetPackComposeTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Composable
fun GreetingsList(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}


@Composable
fun Greeting(name: String) {

    var extended by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        if (extended) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),

        ) {
         Row(modifier = Modifier.padding(24.dp)) {
             Column(modifier = Modifier
                 .weight(1f)
                 .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                 Text(text = "Hello,")
                 Text(text = name)
                 if (extended) {
                     Text(
                         text = ("Composem ipsum color sit lazy, " +
                                 "padding theme elit, sed do bouncy. ").repeat(4),
                     )
                 }
             }
             IconButton(onClick = { extended = !extended }) {
                 Icon(
                     imageVector = if (extended) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                     contentDescription = null

                 )
             }
         }

    }



}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticeJetPackComposeTheme {
        Greeting(name = "Abdullah")
    }
}
package com.rezoo.ktor_restapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.rezoo.ktor_restapi.ui.theme.KtorRestApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorRestApiTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val cat = viewModel.state.value.cat
                    val isLoading = viewModel.state.value.isLoading
                    cat?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = cat.imageUrl,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "cat"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = cat.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cat.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = viewModel::getRandomCat,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next cat!")
                    }
                    Spacer(Modifier.height(8.dp))
                    if(isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

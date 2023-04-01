/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.template.ui

import android.template.domain.model.MyModel
import android.template.presentation.viewmodel.MyModelUiState
import android.template.presentation.viewmodel.MyModelViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.template.ui.theme.MyApplicationTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import org.koin.androidx.compose.getViewModel

@Composable
fun FirstScreen(modifier: Modifier = Modifier, viewModel: MyModelViewModel = getViewModel()) {

    val myModelState by viewModel.myModelState.collectAsState()

    when (myModelState) {
        is MyModelUiState.Loading -> ShowProgress()
        is MyModelUiState.Success -> {
            val data = (myModelState as MyModelUiState.Success).data
            MyModelScreen(modifier = modifier, model = data)
        }
        is MyModelUiState.Error -> (myModelState as MyModelUiState.Error).message
    }
}

@Composable
fun ShowProgress() {
    CircularProgressIndicator()
}

@Composable
fun MyModelScreen(
    modifier: Modifier = Modifier,
    model: MyModel,
) {
    Box(modifier = modifier.background(Color.Red)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = model.name)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {}) {
                Text("Navigate to second screen")
            }
        }
    }

}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        MyModelScreen(model = MyModel("Compose", 2))
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        MyModelScreen(model = MyModel("Compose", 2))
    }
}

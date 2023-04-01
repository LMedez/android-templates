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

package android.template.presentation.viewmodel

import android.template.data.ResultStatus
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.template.domain.MyModelRepository
import android.template.domain.model.MyModel
import android.util.Log
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyModelViewModel(
    private val myModelRepository: MyModelRepository
) : ViewModel() {

    private val _myModelState = MutableStateFlow<MyModelUiState>(MyModelUiState.Loading)
    val myModelState = _myModelState.asStateFlow()

    init {
        viewModelScope.launch {
            _myModelState.value = when(val result = myModelRepository.getMyModel("1")) {
                is ResultStatus.Success -> MyModelUiState.Success(result.data)
                is ResultStatus.Error -> MyModelUiState.Error(result.exception?.message)
            }
        }
    }
}

sealed interface MyModelUiState {
    object Loading : MyModelUiState
    data class Error(val message: String?) : MyModelUiState
    data class Success(val data: MyModel) : MyModelUiState
}
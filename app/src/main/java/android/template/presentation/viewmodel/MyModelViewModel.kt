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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.template.domain.MyModelRepository
import android.template.domain.model.MyModel
import kotlinx.coroutines.flow.*

class MyModelViewModel(
    private val myModelRepository: MyModelRepository
) : ViewModel() {

    fun getMyModel(id: String): StateFlow<ResultStatus<MyModel>> {
        return myModelRepository
            .getMyModel(id).map<MyModel, ResultStatus<MyModel>>{value -> ResultStatus.Success(value) }
            .catch { emit(ResultStatus.Error(it.message)) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultStatus.Loading)
    }
}

sealed class ResultStatus <out T> {
    object Loading: ResultStatus<Nothing>()
    data class Success<out T>(val data : T) : ResultStatus<T>()
    data class Error<out T>(val message: String? = null) : ResultStatus<T>()
}

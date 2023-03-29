package android.template.presentation.di

import android.template.presentation.mymodel.MyModelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MyModelViewModel(get()) }
}
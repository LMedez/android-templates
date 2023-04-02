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

package android.template.data.di

import android.app.Application
import android.template.data.local.LocalDataSource
import android.template.domain.MyModelRepository
import android.template.data.local.LocalDatabase
import android.template.data.local.dao.MyModelDao
import android.template.data.remote.firebase.FirestoreDataSource
import android.template.data.repository.MyModelRepositoryImpl
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val firestoreModule = module {
    single { FirebaseFirestore.getInstance() }
    single {
        FirestoreDataSource(
            firebaseFirestore = get(),
        )
    }
}

private val repositoryModule = module {
    factory { LocalDataSource(get()) }
    factory<MyModelRepository> {
        MyModelRepositoryImpl(get(), get())
    }
}

private val roomModule = module {
    fun provideDatabase(application: Application): LocalDatabase {
        return Room.databaseBuilder(application, LocalDatabase::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMyModelDao(database: LocalDatabase): MyModelDao {
        return database.myModelDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideMyModelDao(get()) }
}

val dataModule = listOf(roomModule, repositoryModule, firestoreModule)
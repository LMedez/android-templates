package android.template.data.repository

import android.template.data.ResultStatus
import android.template.data.local.LocalDataSource
import android.template.data.remote.firebase.FirestoreDataSource
import android.template.domain.MyModelRepository
import android.template.domain.model.MyModel
import kotlinx.coroutines.delay

class MyModelRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val firestoreDataSource: FirestoreDataSource
) : MyModelRepository {
    override suspend fun getMyModel(id: String): ResultStatus<MyModel> {
        return try {
            // This is the normal code you call when room has data
            // val localData = localDataSource.getMyModelEntity(id).asMyModel()
            delay(3000)
            ResultStatus.Success(MyModel("Model Data", 1))
        } catch (e: Exception) {
            ResultStatus.Error(e)
        }
    }

    override suspend fun saveMyModel(myModel: MyModel) {
        localDataSource.saveMyModel(myModel)
    }
}

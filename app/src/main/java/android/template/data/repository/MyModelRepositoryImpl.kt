package android.template.data.repository

import android.template.data.ResultStatus
import android.template.data.local.LocalDataSource
import android.template.domain.MyModelRepository
import android.template.domain.model.MyModel
import android.template.domain.model.asMyModel
import android.util.Log

class MyModelRepositoryImpl(
    private val localDataSource: LocalDataSource
) : MyModelRepository {
    override suspend fun getMyModel(id: String): ResultStatus<MyModel> {
        return try {
            val localData = localDataSource.getMyModelEntity(id).asMyModel()
            ResultStatus.Success(MyModel("lucho", 2))
        } catch (e: Exception) {
            ResultStatus.Error(e)
        }
    }

    override suspend fun saveMyModel(myModel: MyModel) {
        localDataSource.saveMyModel(myModel)
    }
}

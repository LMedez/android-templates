package android.template.data.repository

import android.template.data.local.LocalDataSource
import android.template.data.local.entities.MyModelEntity
import android.template.domain.MyModelRepository
import android.template.domain.model.MyModel
import android.template.domain.model.asMyModel
import kotlinx.coroutines.flow.flow

class MyModelRepositoryImpl(
    private val localDataSource: LocalDataSource
) : MyModelRepository {
    override fun getMyModel(id: String) = flow<MyModel> { localDataSource.getMyModelEntity(id).asMyModel()}

    override suspend fun saveMyModel(myModel: MyModel) {
        localDataSource.saveMyModel(myModel)
    }
}

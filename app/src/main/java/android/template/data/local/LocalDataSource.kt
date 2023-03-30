package android.template.data.local

import android.template.data.local.dao.MyModelDao
import android.template.data.local.entities.asMyModelEntity
import android.template.domain.model.MyModel

class LocalDataSource(private val myModelDao: MyModelDao) {
    suspend fun getMyModelEntity(id: String) = myModelDao.getMyModelEntity(id)
    suspend fun saveMyModel(myModel: MyModel) = myModelDao.insert(myModel.asMyModelEntity())
}
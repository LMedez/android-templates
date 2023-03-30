package android.template.data.local.dao

import android.template.data.local.entities.MyModelEntity
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class MyModelDao: BaseDao<MyModelEntity> {

    // TODO("create the necessary abstract methods for this model")

    @Query("SELECT * FROM mymodelentity WHERE uid = :uid")
    abstract suspend fun getMyModelEntity(uid: String): MyModelEntity

}

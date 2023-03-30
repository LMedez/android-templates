package android.template.domain.model

import android.template.data.local.entities.MyModelEntity

data class MyModel(val name: String, val uid: Int)

fun MyModelEntity.asMyModel() = MyModel(
    this.name,
    this.uid
)

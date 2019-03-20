package com.example.facedetection.data.local.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "image_db_obj")
data class ImageObj(
    @PrimaryKey @ColumnInfo(name = "path") var path: String,
    @ColumnInfo(name = "image_type") @IImageObj.Companion.Type var type: String
) : IImageObj {
    override fun getImageType(): String = type

    override fun getImagePath(): String = path

    override fun areItemsTheSame(obj: IImageObj): Boolean =
        this.getImagePath() == obj.getImagePath()

    override fun areContentsTheSame(obj: IImageObj): Boolean =
        this.getImagePath() == obj.getImagePath()
}
package com.example.facedetection.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.facedetection.data.local.model.ImageObj
import io.reactivex.Flowable

/**
 * Data Access Object for the image table.
 */
@Dao
interface ImageDao {

    /**
     * Get all [ImageObj].
     * @return all images.
     */
    @Query("SELECT * FROM image_db_obj")
    fun getAllImages(): Flowable<List<ImageObj>>

    /**
     * Insert a [ImageObj] in the database. If the [ImageObj] already exists, replace it.
     * @param image the image to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImageObj): Long

    /**
     * Delete all [ImageObj]'s.
     */
    @Query("DELETE FROM image_db_obj")
    fun deleteAllImages()
}
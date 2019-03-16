package com.example.facedetection.utils.diff

/**
 * Created with care by Alexey.T
 *
 * If you use {@link android.support.v7.util.DiffUtil} so just override this interface in object that you need
 */
interface IDiff<TYPE> {
    fun areItemsTheSame(obj: TYPE): Boolean

    fun areContentsTheSame(obj: TYPE): Boolean
}
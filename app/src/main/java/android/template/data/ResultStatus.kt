package android.template.data

sealed class ResultStatus<out T> {
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error<out T>(val exception: Exception? = null) : ResultStatus<T>()
}

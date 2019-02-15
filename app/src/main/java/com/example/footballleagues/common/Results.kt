package com.example.footballleagues.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.observers.DisposableSingleObserver

/**
 * This concept is borrowed from Google IO018 app
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

/**
 * This concept is also borrowed from Google IO018 app
 */
sealed class FBResult<out T> {
    data class Loading(val loading: Boolean) : FBResult<Nothing>()
    data class Success<T>(val data: T) : FBResult<T>()
    data class Error(val error: Exception) : FBResult<Nothing>()
}


class CustomDisposable<T>(private val loading:Boolean) : DisposableSingleObserver<T>() {
    private var result: FBResult<T>
    private val resultLiveData = MutableLiveData<FBResult<T>>()
    init {
        result = FBResult.Loading(loading)
        resultLiveData.postValue(result)
    }

    override fun onSuccess(t: T) {
        result = FBResult.Success(t)
        resultLiveData.postValue(result)

    }

    override fun onError(e: Throwable) {
        result = FBResult.Error(e as Exception)
        resultLiveData.postValue(result)

    }

    fun getCallingResult(): LiveData<FBResult<T>> {

        return resultLiveData
    }

}
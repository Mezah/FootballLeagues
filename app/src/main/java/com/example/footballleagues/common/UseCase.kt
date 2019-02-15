package com.example.footballleagues.common

import io.reactivex.disposables.CompositeDisposable

open class UseCase {
    val compositeDisposable = CompositeDisposable()

    fun clear(){
        compositeDisposable.dispose()
    }
}
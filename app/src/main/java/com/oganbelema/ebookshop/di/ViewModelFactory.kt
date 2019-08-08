package com.oganbelema.ebookshop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Belema Ogan on 2019-08-08.
 */
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModels[modelClass]
        return if (provider != null) {
            provider.get() as T
        } else {
            throw IllegalArgumentException("Can't find provider for ViewModel class ${modelClass.simpleName}")
        }
    }
}
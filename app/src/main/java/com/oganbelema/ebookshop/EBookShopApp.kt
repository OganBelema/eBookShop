package com.oganbelema.ebookshop

import android.app.Application
import com.oganbelema.ebookshop.di.AppComponent
import com.oganbelema.ebookshop.di.AppModule
import com.oganbelema.ebookshop.di.DaggerAppComponent

/**
 * Created by Belema Ogan on 2019-08-08.
 */
class EBookShopApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}
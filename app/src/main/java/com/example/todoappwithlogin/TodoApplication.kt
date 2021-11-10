

package com.example.todoappwithlogin

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.example.todoappwithlogin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * An application that lazily provides a repository. Note that this Service Locator pattern is
 * used to simplify the sample. Consider a Dependency Injection framework.
 *
 * Also, sets up Timber in the DEBUG BuildConfig. Read Timber's documentation for production setups.
 */
class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        startKoin {
            androidContext(
                this@TodoApplication
            )
            androidLogger()
            modules(
                listOf(
                    dbInstance,
                    localRepository,
                    remoteRepository,
                    defaultRepository,
                    viewModelInstance,
                    viewModelAddEdit,
                    viewModelStatistics,
                    viewModelDetail
                )
            )
        }
    }
}

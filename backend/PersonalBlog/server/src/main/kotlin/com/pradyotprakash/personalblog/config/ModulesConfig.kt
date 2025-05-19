package com.pradyotprakash.personalblog.config

import com.pradyotprakash.personalblog.core.database.PersonalBlogSupabaseClient
import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.data.blog.SupabaseBlogDataSource
import com.pradyotprakash.personalblog.features.blog.controllers.BlogController
import com.pradyotprakash.personalblog.features.blog.controllers.add.BlogAddController
import com.pradyotprakash.personalblog.features.blog.controllers.add.BlogAddControllerImplementation
import com.pradyotprakash.personalblog.features.blog.controllers.delete.BlogDeleteController
import com.pradyotprakash.personalblog.features.blog.controllers.delete.BlogDeleteControllerImplementation
import com.pradyotprakash.personalblog.features.blog.controllers.fetch.BlogFetchController
import com.pradyotprakash.personalblog.features.blog.controllers.fetch.BlogFetchControllerImplementation
import com.pradyotprakash.personalblog.features.blog.controllers.update.BlogUpdateController
import com.pradyotprakash.personalblog.features.blog.controllers.update.BlogUpdateControllerImplementation
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object ModulesConfig {
    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { PersonalBlogSupabaseClient.getDatabase() }

        bindSingleton<BlogDataSource> { SupabaseBlogDataSource(instance()) }
    }

    private val controllersModule = DI.Module("CONTROLLERS") {
        bindProvider<BlogAddController> { BlogAddControllerImplementation(instance()) }
        bindProvider<BlogFetchController> { BlogFetchControllerImplementation(instance()) }
        bindProvider<BlogUpdateController> { BlogUpdateControllerImplementation(instance()) }
        bindProvider<BlogDeleteController> { BlogDeleteControllerImplementation(instance()) }
    }

    private val featuresModule = DI.Module("FEATURES") {
        bindProvider { BlogController(instance(), instance(), instance(), instance()) }
    }

    val di = DI {
        importAll(
            databaseModule,
            controllersModule,
            featuresModule,
        )
    }
}
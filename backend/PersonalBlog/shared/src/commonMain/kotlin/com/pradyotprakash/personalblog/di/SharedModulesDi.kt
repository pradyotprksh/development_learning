package com.pradyotprakash.personalblog.di

import com.pradyotprakash.personalblog.core.network.NetworkClient
import com.pradyotprakash.personalblog.core.network.PersonalBlogHttpClient
import com.pradyotprakash.personalblog.domain.repositories.personalBlog.PersonalBlogRepository
import com.pradyotprakash.personalblog.domain.repositories.personalBlog.PersonalBlogRepositoryImplementation
import com.pradyotprakash.personalblog.service.personalBlog.PersonalBlogService
import com.pradyotprakash.personalblog.service.personalBlog.PersonalBlogServiceImplementation
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

object SharedModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton { PersonalBlogHttpClient.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
    }

    private val servicesRemoteModule = DI.Module("SERVICES_REMOTE") {
        bindProvider<PersonalBlogService> { PersonalBlogServiceImplementation(instance()) }
    }

    private val repositoriesModule = DI.Module("REPOSITORIES") {
        bind<PersonalBlogRepository>() with singleton {
            PersonalBlogRepositoryImplementation(
                instance(),
            )
        }
    }

    val di = DI {
        importAll(
            networkModule,
            servicesRemoteModule,
            repositoriesModule,
        )
    }

    object Instance {
        val personalBlogRepository: PersonalBlogRepository by di.instance()
    }
}
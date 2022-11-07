package ru.ozon.route256.core_navigation_impl.di

import dagger.Component
import ru.ozon.route256.core_navigation_api.NavigationApi
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface CoreNavigationComponent: NavigationApi
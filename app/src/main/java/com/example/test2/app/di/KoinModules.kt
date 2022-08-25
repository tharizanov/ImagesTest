package com.example.test2.app.di

import com.example.test2.app.domains.DomainConvertor
import com.example.test2.app.ui.fragments.details.DetailsFragment
import com.example.test2.app.ui.fragments.home.HomeFragment
import com.example.test2.app.ui.fragments.home.HomeVM
import com.example.test2.app.ui.fragments.home.adapter.HomeRecyclerAdapter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { HomeFragment(get()) }
    fragment { DetailsFragment() }
}

val viewModelModule = module {
    viewModel { HomeVM(get(), get(), get(), get()) }
}

val toolModule = module {
    single { DomainConvertor() }
    factory { HomeRecyclerAdapter(get()) }
}
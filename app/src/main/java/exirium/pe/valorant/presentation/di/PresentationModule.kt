package exirium.pe.valorant.presentation.di

import exirium.pe.valorant.presentation.screen.agents.AgentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { AgentsViewModel(get()) }
}
package exirium.pe.valorant.domain.di

import exirium.pe.valorant.data.repository.AgentRepositoryImpl
import exirium.pe.valorant.domain.repository.AgentRepository
import exirium.pe.valorant.domain.usecase.GetAgentsUseCase
import org.koin.dsl.module

val domainModule = module {
    single<AgentRepository> { AgentRepositoryImpl(get()) }
    single { GetAgentsUseCase(get()) }
}
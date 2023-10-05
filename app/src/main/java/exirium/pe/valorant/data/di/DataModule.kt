package exirium.pe.valorant.data.di

import exirium.pe.valorant.data.remote.service.ValorantApi
import exirium.pe.valorant.data.repository.AgentRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single { get<Retrofit>().create(ValorantApi::class.java) }
    single { AgentRepositoryImpl(get()) }
}
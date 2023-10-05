package exirium.pe.valorant

import android.app.Application
import exirium.pe.valorant.di.appModule
import exirium.pe.valorant.data.di.dataModule
import exirium.pe.valorant.domain.di.domainModule
import exirium.pe.valorant.presentation.di.presentationModule
import org.koin.core.context.GlobalContext

class ValorantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            modules(appModule)
            modules(dataModule)
            modules(domainModule)
            modules(presentationModule)
        }
    }
}
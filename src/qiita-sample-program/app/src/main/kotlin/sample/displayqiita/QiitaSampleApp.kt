package sample.displayqiita

import android.app.Application
import dagger.Component
import sample.displayqiita.dagger.AppComponent
import sample.displayqiita.dagger.RequestModule
import javax.inject.Singleton

open class QiitaSampleApp : Application() {

    val component: AppComponent = createComponent()

    open fun createComponent(): AppComponent =
            DaggerQiitaClientApp_QiitaClientAppComponent.create()

    @Component(modules = arrayOf(RequestModule::class))
    @Singleton
    interface QiitaClientAppComponent : AppComponent
}
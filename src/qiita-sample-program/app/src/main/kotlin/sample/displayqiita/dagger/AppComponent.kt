package sample.displayqiita.dagger

import sample.displayqiita.MainActivity

/**
 * アプリのコンポーネントインターフェースです
 */
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
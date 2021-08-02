package geekbrains.ru.translator.presenter

import geekbrains.ru.translator.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}

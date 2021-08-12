package geekbrains.ru.translator.di

import geekbrains.ru.translator.model.data.DataModel
import geekbrains.ru.translator.model.datasource.RetrofitImpl
import geekbrains.ru.translator.model.datasource.RoomDataBaseImpl
import geekbrains.ru.translator.model.repository.Repository
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import geekbrains.ru.translator.viewModel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val REMOTE_REPO: String = "remote repo"
const val LOCAL_REPO: String = "local repo"

val application = module {
    single<Repository<List<DataModel>>>(named(REMOTE_REPO)) {
        RepositoryImplementation(RetrofitImpl())
    }
    single<Repository<List<DataModel>>>(named(LOCAL_REPO)) {
        RepositoryImplementation(RoomDataBaseImpl())
    }
}

val mainScreen = module {
    factory { MainViewModel() }
}
package geekbrains.ru.translator.model.repository

import geekbrains.ru.translator.model.data.DataModel
import geekbrains.ru.translator.model.datasource.DataSource
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}

package geekbrains.ru.translator.model.datasource

import geekbrains.ru.translator.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal(private val localProvider: RoomDataBaseImpl = RoomDataBaseImpl()) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = localProvider.getData(word)
}

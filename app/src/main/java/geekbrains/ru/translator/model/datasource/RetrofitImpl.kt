package geekbrains.ru.translator.model.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import geekbrains.ru.translator.model.data.DataModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : DataSource<List<DataModel>> {
    private val api: ApiService by lazy {
        return@lazy retrofit.create(ApiService:: class.java)
    }
    private val retrofit: Retrofit by lazy {
        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    override suspend fun getData(word: String): List<DataModel> {
        return api.searchAsync(word).await()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}

package geekbrains.ru.translator.model.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import geekbrains.ru.translator.model.data.DataModel
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    override fun getData(word: String): Observable<List<DataModel>> {
        return api.search(word)
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}

package geekbrains.ru.translator.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.ru.translator.model.data.AppState
import geekbrains.ru.translator.model.data.DataModel
import geekbrains.ru.translator.model.datasource.DataSourceLocal
import geekbrains.ru.translator.model.datasource.DataSourceRemote
import geekbrains.ru.translator.model.repository.Repository
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import geekbrains.ru.translator.presenter.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    var liveData: MutableLiveData<AppState> = MutableLiveData()
    private val remoteRepository: Repository<List<DataModel>> = RepositoryImplementation(
        DataSourceRemote()
    )
    private val localRepository: Repository<List<DataModel>> = RepositoryImplementation(
        DataSourceLocal()
    )
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getData(word: String, isOnline: Boolean) {
        val data = if (isOnline) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
        compositeDisposable.add(
            data
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                liveData.value = appState
            }

            override fun onError(e: Throwable) {
                liveData.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

}
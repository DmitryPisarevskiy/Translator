package geekbrains.ru.translator.presenter

import geekbrains.ru.translator.model.data.AppState
import geekbrains.ru.translator.model.data.DataModel
import geekbrains.ru.translator.model.datasource.DataSourceLocal
import geekbrains.ru.translator.model.datasource.DataSourceRemote
import geekbrains.ru.translator.model.repository.Repository
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter<T : AppState, V : View>(

    private val remoteRepository: Repository<List<DataModel>> = RepositoryImplementation(
        DataSourceRemote()
    ),
    private val localRepository: Repository<List<DataModel>> = RepositoryImplementation(
        DataSourceLocal()
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    private var currentView: V? = null

    fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

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
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}

package app.web.finecloud.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import app.web.finecloud.domain.products.usecase.GetBeersListByCoroutineParams
import app.web.finecloud.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.finecloud.domain.products.usecase.GetBeersListParams
import app.web.finecloud.domain.products.usecase.GetBeersListUseCase
import app.web.finecloud.presentation.base.adapter.RecyclerItem
import app.web.finecloud.presentation.base.viewmodel.BaseViewModel
import app.web.finecloud.presentation.products.choose.ChoosePathType
import app.web.finecloud.presentation.products.entity.BeerMapper
import autodispose2.autoDispose
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersListUseCase,
    private val getBeersListByCoroutineUseCase: GetBeersListByCoroutineUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<RecyclerItem>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<RecyclerItem>> = _ldProductsList

    private val _productsListByCoroutine =
        MutableStateFlow<PagingData<RecyclerItem>>(PagingData.empty())
    val productsListByCoroutine: Flow<PagingData<RecyclerItem>> = _productsListByCoroutine


    init {
        val path = savedStateHandle.get<ChoosePathType>("choosePathType")
        Timber.d("Which path: $path")
        getProductsBaseOnPath("", path)
    }

    private fun getProductsByRxPath(ids: String) {
        getBeersUseCase(GetBeersListParams(ids = ids))
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe { pagingDataBeer ->
                _ldProductsList.value = pagingDataBeer
                    .map { beer ->
                        BeerMapper().mapLeftToRight(beer)
                    }
            }
    }

    private fun getProductsByCoroutinePath(ids: String) =
        getBeersListByCoroutineUseCase(GetBeersListByCoroutineParams(ids = ids))
            .cachedIn(viewModelScope)

    private fun getProductsBaseOnPath(ids: String, path: ChoosePathType?) {
        when (path) {
            ChoosePathType.RX -> {
                getProductsByRxPath(ids)
            }
            ChoosePathType.COROUTINE -> {
                viewModelScope.launch {
                    _productsListByCoroutine.value = getProductsByCoroutinePath(ids).first()
                        .map { beer ->
                            BeerMapper().mapLeftToRight(beer)
                        }
                }
            }
            else -> getProductsByRxPath(ids)
        }
    }

}
package app.web.finecloud.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import app.web.finecloud.data.products.datasource.ProductsPagingSource
import app.web.finecloud.data.products.datasource.ProductsPagingSourceByCoroutine
import app.web.finecloud.domain.extension.allowReads
import app.web.finecloud.domain.products.entity.Beer
import app.web.finecloud.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsListRepositoryImpl @Inject constructor(
    private val pagingSource: ProductsPagingSource,
    private val pagingSourceByCoroutine: ProductsPagingSourceByCoroutine
) : ProductsListRepository {

    override fun getBeersList(ids: String): Flowable<PagingData<Beer>> =
        allowReads {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 10,
                    jumpThreshold = 60
                ),
                pagingSourceFactory = { pagingSource }
            ).flowable
        }

    override fun getBeersListByCoroutine(ids: String): Flow<PagingData<Beer>> =
        allowReads {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 10,
                    jumpThreshold = 60
                ),
                pagingSourceFactory = { pagingSourceByCoroutine }
            ).flow
        }
}
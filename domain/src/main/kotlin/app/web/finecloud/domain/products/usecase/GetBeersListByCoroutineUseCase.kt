package app.web.finecloud.domain.products.usecase

import androidx.paging.PagingData
import app.web.finecloud.domain.base.usecase.GeneralUseCase
import app.web.finecloud.domain.products.entity.Beer
import app.web.finecloud.domain.products.repository.ProductsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeersListByCoroutineUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository,
) : GeneralUseCase<Flow<PagingData<Beer>>, GetBeersListByCoroutineParams> {

    override fun invoke(params: GetBeersListByCoroutineParams): Flow<PagingData<Beer>> =
        productsListRepository.getBeersListByCoroutine(params.ids)

}

@JvmInline
value class GetBeersListByCoroutineParams(val ids: String)
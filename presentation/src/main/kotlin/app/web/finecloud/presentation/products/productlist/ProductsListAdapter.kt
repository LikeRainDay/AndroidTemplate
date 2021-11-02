package app.web.finecloud.presentation.products.productlist

import android.view.View
import app.web.finecloud.presentation.base.adapter.BasePagedListAdapter
import app.web.finecloud.presentation.base.adapter.RecyclerItem
import app.web.finecloud.presentation.products.entity.BeerCell

class ProductsListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    BeerCell,
    onItemClick = onItemClick
)
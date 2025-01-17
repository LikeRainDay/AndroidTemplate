package app.web.finecloud.presentation.base.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import app.web.finecloud.presentation.databinding.ItemLoadingRowBinding

class LoadingStateViewHolder(private val itemBinding: ItemLoadingRowBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(loadState: LoadState) {
        itemBinding.itemLoadingRowContainer.isVisible = loadState is LoadState.Loading
    }

}
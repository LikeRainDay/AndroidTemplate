package app.web.finecloud.presentation.base.adapter

interface RecyclerItem {
    val id: Int?
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}
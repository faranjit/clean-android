package com.faranjit.clean.core.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bulent Turkmen on 1.04.2020.
 */
abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>>(
    items: List<T>,
    private val clickListener: ((T, VH) -> Unit)? = null
) :
    RecyclerView.Adapter<VH>() {

    protected var data: MutableList<T> = items.toMutableList()

    override fun onBindViewHolder(holder: VH, position: Int) {
        data[position]?.also { item ->
            holder.bind(item)

            holder.itemView.setOnClickListener {
                clickListener?.invoke(item, holder)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun addItems(newItems: List<T>) {
        val count = itemCount
        data.addAll(newItems)

        notifyItemRangeInserted(count, newItems.size)
    }
}

abstract class BaseViewHolder<T>(binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}
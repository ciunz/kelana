package sen.com.absraction.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_item_error.view.*
import kotlinx.android.synthetic.main.simple_item_loading.view.*
import sen.com.absraction.R
import sen.com.absraction.utils.ItemMoveCallback
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by korneliussendy on 01/03/20,
 * at 18.04.
 * My Application
 */

abstract class BaseAdapter<I, VH : RecyclerView.ViewHolder>(@LayoutRes var cell: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemMoveCallback.ItemTouchHelperContract {

    private var items = ArrayList<I?>()
    private val TYPE_ERROR = -1
    private val TYPE_LOAD = 0
    protected val TYPE_ITEM = 1
    private var dragable = false

    private var msg: String? = null

    private var errorMsg: String? = null
    private var errorButtonText: String? = null
    private var error = false
    private var onErrorClick: (() -> Unit)? = null

    open var onClick: ((v: View) -> Unit)? = null
    open var onItemClick: ((item: I, pos: Int) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) if (error) TYPE_ERROR else TYPE_LOAD else TYPE_ITEM
    }

    fun setItems(items: ArrayList<I?>?) {
        items?.let { this.items = items }
    }

    fun getAllItems(): ArrayList<I?> = items

    override fun getItemCount(): Int = items.size

    fun getItem(pos: Int): I? {
        return items[pos]
    }

    open fun addItem(item: I?) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    open fun addItem(item: I?, pos: Int) {
        if (pos >= items.size) return
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    fun addItems(items: List<I>) {
        val count = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(count, itemCount - 1)
    }

    fun updateItem(item: I?, position: Int) {
        if (position > items.size - 1) return
        items[position] = item
        notifyItemChanged(position)
    }

    fun notifyConfigChanged() {
        notifyItemRangeChanged(0, items.size)
    }

    fun showLoader() {
        hideError()
        for (i in 1..loadingItemCount())
            addItem(null)
        msg = "Loading"
    }

    fun hideLoader() {
        removeLast()
        msg = ""
    }

    fun showError() = showError("Error", "", null)

    fun showError(errorMessage: String?) = showError(errorMessage, "", null)

    fun showError(errorMessage: String?, btnText: String?, onErrorClick: (() -> Unit)?) {
        hideError()
        this.errorMsg = errorMessage
        this.onErrorClick = onErrorClick
        this.errorButtonText = btnText
        error = true
        addItem(null)
    }

    fun hideError() {
        if (!error) return
        this.errorMsg = ""
        error = false
//        if (items[itemCount - 1] == null)
        removeLast()
    }

    fun positionExist(pos: Int) = pos >= 0 && pos < items.size

    open fun remove(position: Int) {
        if (items.isEmpty() || !positionExist(position)) return
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun removeLast() {
        for (i in 1..loadingItemCount())
            if (itemCount > 0) {
                items.removeAt(itemCount - 1)
                notifyItemRemoved(itemCount)
            }
    }

    open fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    open fun loadingLayout(): Int = 0

    open fun errorLayout(): Int = 0

    open fun loadingItemCount(): Int = 1

    fun getColor(context: Context, @ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val resLayout = when (viewType) {
            TYPE_ERROR -> if (errorLayout() > 0) errorLayout() else R.layout.simple_item_error
            TYPE_LOAD -> if (loadingLayout() > 0) loadingLayout() else R.layout.simple_item_loading
            else -> cell

        }
        val v = inflater.inflate(
            resLayout, parent, false
        )
        return when (viewType) {
            TYPE_ERROR -> ErrorViewHolder(v, errorLayout() > 0)
            TYPE_LOAD -> LoaderViewHolder(v, loadingLayout() > 0)
            else -> createView(v, viewType)

        }
    }

    abstract fun createView(view: View, viewType: Int): VH

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoaderViewHolder -> {
                holder.bind(msg)
            }
            is ErrorViewHolder -> {
                holder.bind(errorMsg, errorButtonText, onErrorClick)
            }
            else -> {
                val item = items[position]
                @Suppress("UNCHECKED_CAST")
                if (item != null) onBindView(holder as VH, item, position)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return items[position].hashCode().toLong()
    }

    abstract fun onBindView(holder: VH, item: I, pos: Int)

    class LoaderViewHolder(private var v: View, private var custom: Boolean) :
        RecyclerView.ViewHolder(v) {
        fun bind(msg: String?) {
            if (custom) return
            v.tvInfo.visibility = if (msg.isNullOrEmpty()) View.GONE else View.VISIBLE
            v.tvInfo.text = msg
        }
    }

    class ErrorViewHolder(var v: View, private var custom: Boolean) : RecyclerView.ViewHolder(v) {
        fun bind(msg: String?, btnText: String?, onErrorClick: (() -> Unit)?) {
            if (custom) return
            v.tvInfoError.visibility = if (msg.isNullOrEmpty()) View.GONE else View.VISIBLE
            v.tvInfoError.text = msg
            v.btnAction.text = btnText
            if (onErrorClick != null) {
                v.btnAction.visibility = View.VISIBLE
                v.btnAction.setOnClickListener { onErrorClick() }
            } else {
                v.btnAction.visibility = View.GONE
            }
        }
    }

    /***
     * DRAG IMPLEMMENTATION
     */

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
}
package me.qidonk.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import me.qidonk.footballapp.DetailActivity
import me.qidonk.footballapp.R
import me.qidonk.footballapp.entity.Item
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class RecyclerViewAdapter(private val context: Context, private val items: List<Item>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(item: Item) {
            itemView.name.text = item.name
            item.image?.let { Picasso.get().load(it).into(image) }
            itemView.onClick {
                itemView.context.startActivity<DetailActivity>(
                    "name" to item.name,
                    "image" to item.image,
                    "desc" to item.description
                )
            }
        }
    }
}
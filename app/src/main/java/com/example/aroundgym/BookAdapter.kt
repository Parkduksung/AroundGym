package com.example.aroundgym

import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.aroundgym.data.model.Document
import com.example.aroundgym.util.ImageUtil

class BookAdapter : BaseAdapter() {

    private val bookList = mutableListOf<Pair<Document, Boolean>>()

    private var bookViewHolder: BookViewHolder? = null

    private lateinit var view: View

    private lateinit var itemClickListener: (item: Pair<Document, Boolean>) -> Unit

    override fun getCount(): Int =
        bookList.size

    override fun getItemId(position: Int): Long =
        position.toLong()

    override fun getItem(position: Int): Document =
        bookList[position].first

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
            bookViewHolder =
                BookViewHolder(view)
            view.tag = bookViewHolder
        } else {
            view = convertView
            bookViewHolder = view.tag as? BookViewHolder
        }
        bookViewHolder?.bind(bookList[position], itemClickListener)

        return view
    }

    fun addAll(list: List<Document>) {
        bookList.addAll(list.map { Pair(it, false) })
        notifyDataSetChanged()
    }

    fun loadNextData(list: List<Document>) {
        bookList.addAll(list.map { Pair(it, false) })
        notifyDataSetChanged()
    }

    fun like(pair: Pair<Document, Boolean>) {
        val index = bookList.indexOf(bookList.find { it.first == pair.first })
        bookList[index] = pair
        notifyDataSetChanged()
    }

    fun clear() {
        bookList.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (item: Pair<Document, Boolean>) -> Unit) {
        itemClickListener = listener
    }
}

class BookViewHolder(private val itemView: View) {

    private val image: ImageView = itemView.findViewById(R.id.image)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val content: TextView = itemView.findViewById(R.id.content)
    private val bookmark: ImageView = itemView.findViewById(R.id.like)

    fun bind(item: Pair<Document, Boolean>, listener: (item: Pair<Document, Boolean>) -> Unit) {

        itemView.setOnClickListener {
            listener(item)
        }

        if (item.second) {
            bookmark.setImageResource(R.drawable.ic_like_on)
        } else {
            bookmark.setImageResource(R.drawable.ic_like_off)
        }

        title.text = item.first.title
        content.text = item.first.title
        ImageUtil.setBitmapImage(item.first.thumbnail,
            onSuccess = {
                android.os.Handler(Looper.getMainLooper()).post {
                    image.setImageBitmap(it)
                }
            }, onFailure = {
                android.os.Handler(Looper.getMainLooper()).post {
                    image.setImageResource(android.R.drawable.ic_delete)
                }
            })
    }
}
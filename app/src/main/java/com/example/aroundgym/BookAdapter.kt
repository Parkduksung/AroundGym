package com.example.aroundgym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class BookAdapter : BaseAdapter() {

    private val bookList = mutableListOf<Document>()

    private var bookViewHolder: BookViewHolder? = null

    private lateinit var view: View

    override fun getCount(): Int =
        bookList.size

    override fun getItemId(position: Int): Long =
        position.toLong()

    override fun getItem(position: Int): Document =
        bookList[position]

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
        bookViewHolder?.bind(bookList[position])

        return view
    }

    fun addAll(list: List<Document>) {
        bookList.addAll(list)
    }

    fun clear() {
        bookList.clear()
        notifyDataSetChanged()
    }
}

class BookViewHolder(itemView: View) {

    private val image: ImageView = itemView.findViewById(R.id.image)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val content: TextView = itemView.findViewById(R.id.content)

    fun bind(item: Document) {
        title.text = item.title
        content.text = item.title
        ImageUtil.setBitmapImage(item.thumbnail, image)
    }
}
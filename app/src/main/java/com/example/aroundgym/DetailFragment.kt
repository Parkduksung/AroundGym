package com.example.aroundgym

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.aroundgym.util.ImageUtil
import org.w3c.dom.Document

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.getParcelable<com.example.aroundgym.data.model.Document>(KEY_ITEM)?.let { item ->

            with(item) {

                view.findViewById<TextView>(R.id.book_name).text = title
                view.findViewById<TextView>(R.id.book_price).text = sale_price.toString()
                view.findViewById<TextView>(R.id.book_publish_day).text = datetime
                view.findViewById<TextView>(R.id.book_publisher).text = publisher
                view.findViewById<TextView>(R.id.book_detail).text = contents

                Thread {
                    ImageUtil.setBitmapImage(thumbnail,
                        onSuccess = {
                            android.os.Handler(Looper.getMainLooper()).post {
                                view.findViewById<ImageView>(R.id.book_image).setImageBitmap(it)
                            }
                        }, onFailure = {
                            android.os.Handler(Looper.getMainLooper()).post {
                                view.findViewById<ImageView>(R.id.book_image)
                                    .setImageResource(android.R.drawable.ic_delete)
                            }
                        })
                }.start()
            }
        }


        view.findViewById<ImageButton>(R.id.button_route_search).setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    companion object {

        private const val KEY_ITEM = "key_item"

        fun newInstance(item: com.example.aroundgym.data.model.Document) = DetailFragment().apply {
            arguments =
                Bundle().apply {
                    putParcelable(
                        KEY_ITEM,
                        com.example.aroundgym.data.model.Document(
                            authors = item.authors,
                            contents = item.contents,
                            datetime = item.datetime,
                            isbn = item.isbn,
                            price = item.price,
                            publisher = item.publisher,
                            sale_price = item.sale_price,
                            status = item.status,
                            thumbnail = item.thumbnail,
                            title = item.title,
                            translators = item.translators,
                            url = item.url
                        )
                    )
                }
        }

    }
}
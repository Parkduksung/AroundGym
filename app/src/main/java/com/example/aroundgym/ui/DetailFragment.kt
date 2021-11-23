package com.example.aroundgym.ui

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.aroundgym.R
import com.example.aroundgym.data.model.Document
import com.example.aroundgym.util.ImageUtil

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var detailFragmentViewStateListener: DetailFragmentViewStateListener? = null

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


        arguments?.getParcelable<Document>(KEY_ITEM)?.let { item ->

            with(item) {

                view.findViewById<TextView>(R.id.book_name).text = title
                view.findViewById<TextView>(R.id.book_price).text = sale_price.toString()
                view.findViewById<TextView>(R.id.book_publish_day).text = datetime
                view.findViewById<TextView>(R.id.book_publisher).text = publisher
                view.findViewById<TextView>(R.id.book_detail).text = contents


                view.findViewById<CheckBox>(R.id.cb_bookmark)
                    .setOnCheckedChangeListener { buttonView, isChecked ->

                        if (isChecked) {
                            buttonView.background =
                                ContextCompat.getDrawable(requireContext(), R.drawable.ic_like_on)
                        } else {
                            buttonView.background =
                                ContextCompat.getDrawable(requireContext(), R.drawable.ic_like_off)
                        }

                        detailFragmentViewStateListener?.toggleBookmark(Pair(item, isChecked))
                    }

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

        arguments?.getBoolean(KEY_LIKE)?.let {
            view.findViewById<CheckBox>(R.id.cb_bookmark).isChecked = it
        }



        view.findViewById<ImageButton>(R.id.button_route_search).setOnClickListener {
            detailFragmentViewStateListener?.routeSearch()
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
        detailFragmentViewStateListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    interface DetailFragmentViewStateListener {
        fun routeSearch()
        fun toggleBookmark(item: Pair<Document, Boolean>)
    }

    companion object {

        private const val KEY_ITEM = "key_item"
        private const val KEY_LIKE = "key_like"

        fun newInstance(
            item: Pair<Document, Boolean>,
            listener: DetailFragmentViewStateListener
        ) = DetailFragment().apply {
            detailFragmentViewStateListener = listener
            arguments =
                Bundle().apply {
                    putParcelable(
                        KEY_ITEM,
                        Document(
                            authors = item.first.authors,
                            contents = item.first.contents,
                            datetime = item.first.datetime,
                            isbn = item.first.isbn,
                            price = item.first.price,
                            publisher = item.first.publisher,
                            sale_price = item.first.sale_price,
                            status = item.first.status,
                            thumbnail = item.first.thumbnail,
                            title = item.first.title,
                            translators = item.first.translators,
                            url = item.first.url
                        )
                    )
                    putBoolean(KEY_LIKE, item.second)
                }
        }

    }
}
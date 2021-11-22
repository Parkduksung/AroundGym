package com.example.aroundgym

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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

        arguments?.getParcelable<com.example.aroundgym.data.model.Document>(KEY_ITEM)

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
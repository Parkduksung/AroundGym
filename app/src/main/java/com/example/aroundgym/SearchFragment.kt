package com.example.aroundgym

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aroundgym.data.model.Document

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val bookAdapter by lazy { BookAdapter() }

    private val bookList by lazy { view?.findViewById<ListView>(R.id.list_book) }

    private val etInputBook by lazy { view?.findViewById<EditText>(R.id.et_input_book) }

    private val buttonSearch by lazy { view?.findViewById<Button>(R.id.button_search) }

    private var isLoad = false

    private lateinit var searchFragmentViewStateListener: SearchFragmentViewStateListener

    private val mainViewStateListener = object : MainViewStateManager.MainViewState {

        override fun search(list: List<Document>) {
            requireActivity().runOnUiThread {
                bookAdapter.addAll(list)
                isLoad = true
            }
        }

        override fun loadNextData(list: List<Document>) {
            requireActivity().runOnUiThread {
                bookAdapter.loadNextData(list)
                isLoad = true
            }
        }

        override fun error(message: String) {
            requireActivity().runOnUiThread {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private val mainManager by lazy {
        MainViewStateManager().apply {
            setMainViewStateListener(mainViewStateListener)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? SearchFragmentViewStateListener)?.let {
            searchFragmentViewStateListener = it
        }
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
        bookList?.run {
            adapter = bookAdapter
        }

        bookAdapter.setOnItemClickListener {
            searchFragmentViewStateListener.routeDetail(it)
        }

        bookList?.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                val l = visibleItemCount + firstVisibleItem

                if ((l >= totalItemCount) && isLoad) {
                    mainManager.loadNextData()
                    isLoad = false
                }
            }
        })

        buttonSearch?.setOnClickListener {
            bookAdapter.clear()
            mainManager.search(etInputBook?.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
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

    interface SearchFragmentViewStateListener {
        fun routeDetail(item: Document)
    }

    companion object {

        fun newInstance(listener: SearchFragmentViewStateListener) =
            SearchFragment().apply {
                searchFragmentViewStateListener = listener
            }

    }
}

package com.example.aroundgym

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aroundgym.data.model.Document
import com.example.aroundgym.data.repo.KAKAORepositoryImpl
import com.example.aroundgym.data.source.KAKAORemoteDataSourceImpl

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val bookAdapter by lazy { BookAdapter() }

    private val bookList by lazy { findViewById<ListView>(R.id.list_book) }

    private val etInputBook by lazy { findViewById<EditText>(R.id.et_input_book) }

    private val mainViewStateListener = object : MainViewStateManager.MainViewState {
        override fun search(list: List<Document>) {
            runOnUiThread {
                bookAdapter.addAll(list)
            }
        }

        override fun error(message: String) {
            runOnUiThread {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val mainManager by lazy {
        MainViewStateManager(
            KAKAORepositoryImpl.getInstance(
                KAKAORemoteDataSourceImpl.getInstance()
            )
        ).apply {
            setMainViewStateListener(mainViewStateListener)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookList.run {
            adapter = bookAdapter
        }
    }

    fun search(view: View) {
        bookAdapter.clear()
        mainManager.search(etInputBook.text.toString())
    }
}
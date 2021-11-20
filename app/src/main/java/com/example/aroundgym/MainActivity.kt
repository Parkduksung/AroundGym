package com.example.aroundgym

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val bookAdapter by lazy { BookAdapter() }

    private val bookList by lazy { findViewById<ListView>(R.id.list_book) }

    private val apiSearchBook by lazy { ApiSearchBook() }

    private val list = mutableListOf<Document>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookList.run {
            adapter = bookAdapter
        }


        val t = Thread {
            apiSearchBook.search(bookName = "안드로이드")?.let { response ->
                list.addAll(response.documents)
            }
        }

        t.start()

        t.join()

        bookAdapter.addAll(list)
    }

}
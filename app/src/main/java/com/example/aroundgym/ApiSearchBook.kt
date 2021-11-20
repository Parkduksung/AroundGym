package com.example.aroundgym

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ApiSearchBook {

    private fun getURLSearchBook(
        bookName: String,
        size: Int,
        sort: String,
        page: Int,
    ): String =
        "$BASE_URL$SEARCH_BOOK?query=$bookName&size=$size&sort=$sort&page=$page"

    fun search(
        bookName: String,
        size: Int = 50,
        sort: String = "accuracy",
        page: Int = 1,
    ): KakaoSearchBookResponse? {
        return get(bookName, size, sort, page)
    }

    private operator fun get(
        bookName: String,
        size: Int,
        sort: String,
        page: Int,
    ): KakaoSearchBookResponse? {
        val con = connect(getURLSearchBook(bookName, size, sort, page))

        try {
            con.requestMethod = "GET"

            for ((key, value) in HEADER) {
                con.setRequestProperty(key, value)
            }

            val responseCode = con.responseCode

            return if (responseCode == HttpURLConnection.HTTP_OK) {
                val readBody = readBody(con.inputStream)
                val result = parseData(readBody)
                result
            } else {
                null
            }

        } catch (e: IOException) {
            throw RuntimeException("API 요청과 응답 실패", e)
        } finally {
            con.disconnect()
        }
    }

    private fun connect(apiUrl: String): HttpURLConnection {
        try {
            val url = URL(apiUrl)
            return url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException) {
            throw RuntimeException("API URL이 잘못되었습니다. : $apiUrl", e)
        } catch (e: IOException) {
            throw RuntimeException("연결이 실패했습니다. : $apiUrl", e)
        }
    }

    private fun readBody(body: InputStream): String {
        val streamReader = InputStreamReader(body)

        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()

                var line: String? = lineReader.readLine()
                while (line != null) {
                    responseBody.append(line)
                    line = lineReader.readLine()
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }
    }

    private fun parseData(responseBody: String): KakaoSearchBookResponse? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(responseBody)

            val meta = jsonObject.getJSONObject("meta").getMeta()

            val jsonArrayDocuments = jsonObject.getJSONArray("documents")

            val documentList = mutableListOf<Document>()

            for (i in 0 until jsonArrayDocuments.length()) {
                val item = jsonArrayDocuments.getJSONObject(i)

                val authorsList = item.getJSONArray("authors").getAuthors()
                val translatorsList = item.getJSONArray("translators").getTranslators()

                item.getDocument(authors = authorsList, translators = translatorsList)

                documentList.add(
                    item.getDocument(
                        authors = authorsList,
                        translators = translatorsList
                    )
                )
            }


            return KakaoSearchBookResponse(documents = documentList, meta = meta)

        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }


    companion object {
        private const val REST_API_KEY = "7f0bc613532236da7fe88cf3b1bc3160"
        private const val SEARCH_BOOK = "v3/search/book"
        private const val BASE_URL = "https://dapi.kakao.com/"
        private val HEADER = hashMapOf(Pair("Authorization", "KakaoAK $REST_API_KEY"))
    }
}

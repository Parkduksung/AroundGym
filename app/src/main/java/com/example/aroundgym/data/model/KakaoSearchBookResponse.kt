package com.example.aroundgym.data.model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject

data class KakaoSearchBookResponse(
    val documents: List<Document>,
    val meta: Meta
)

data class Document(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val sale_price: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeStringList(authors)
        dest?.writeString(contents)
        dest?.writeString(datetime)
        dest?.writeString(isbn)
        dest?.writeInt(price)
        dest?.writeString(publisher)
        dest?.writeInt(sale_price)
        dest?.writeString(status)
        dest?.writeString(thumbnail)
        dest?.writeString(title)
        dest?.writeStringList(translators)
        dest?.writeString(url)
    }

    companion object CREATOR : Parcelable.Creator<Document> {
        override fun createFromParcel(parcel: Parcel): Document {
            return Document(parcel)
        }

        override fun newArray(size: Int): Array<Document?> {
            return arrayOfNulls(size)
        }
    }
}

data class Meta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)

fun JSONObject.getMeta(): Meta =
    Meta(
        is_end = getBoolean("is_end"),
        pageable_count = getInt("pageable_count"),
        total_count = getInt("total_count")
    )

fun JSONObject.getDocument(authors: List<String>, translators: List<String>): Document =
    Document(
        authors = authors,
        contents = getString("contents"),
        datetime = getString("datetime"),
        isbn = getString("isbn"),
        price = getInt("price"),
        publisher = getString("publisher"),
        sale_price = getInt("sale_price"),
        status = getString("status"),
        thumbnail = getString("thumbnail"),
        title = getString("title"),
        translators = translators,
        url = getString("url")
    )


fun JSONArray.getAuthors(): List<String> {
    val authorList = mutableListOf<String>()
    for (i in 0 until this.length()) {
        authorList.add(getString(i))
    }
    return authorList
}

fun JSONArray.getTranslators(): List<String> {
    val translatorList = mutableListOf<String>()
    for (i in 0 until this.length()) {
        translatorList.add(getString(i))
    }
    return translatorList
}


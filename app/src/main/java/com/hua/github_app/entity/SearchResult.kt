package com.hua.github_app.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import java.util.ArrayList

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class SearchResult<M> {
    @SerializedName("total_count")
    var totalCount: String? = null

    @SerializedName("incomplete_results")
    var isIncompleteResults = false
    var items: ArrayList<M>? = null
}
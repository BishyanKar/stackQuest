package com.example.stackoverquestions.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ReturnedObject {
    @SerializedName("items")
    @Expose
    var questions: List<Question>? = null

    @SerializedName("has_more")
    @Expose
    var hasMore: Boolean? = null

    @SerializedName("quota_max")
    @Expose
    var quotaMax: Int? = null

    @SerializedName("quota_remaining")
    @Expose
    var quotaRemaining: Int? = null
}
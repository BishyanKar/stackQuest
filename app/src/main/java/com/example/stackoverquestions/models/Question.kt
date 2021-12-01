package com.example.stackoverquestions.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Question {

    @SerializedName("question_id")
    @Expose
    @PrimaryKey
    var questionId: Int? = null

//    @SerializedName("tags")
//    @Expose
//    var tags: List<String>? = null
//
//    @SerializedName("owner")
//    @Expose
//    var owner: Owner? = null

    @SerializedName("is_answered")
    @Expose
    var isAnswered: Boolean? = null

    @SerializedName("view_count")
    @Expose
    var viewCount: Int? = null

    @SerializedName("accepted_answer_id")
    @Expose
    var acceptedAnswerId: Int? = null

    @SerializedName("answer_count")
    @Expose
    var answerCount: Int? = null

    @SerializedName("score")
    @Expose
    var score: Int? = null

    @SerializedName("last_activity_date")
    @Expose
    var lastActivityDate: Int? = null

    @SerializedName("creation_date")
    @Expose
    var creationDate: Int? = null

    @SerializedName("last_edit_date")
    @Expose
    var lastEditDate: Int? = null

    @SerializedName("content_license")
    @Expose
    var contentLicense: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("bounty_amount")
    @Expose
    var bountyAmount: Int? = null

    @SerializedName("bounty_closes_date")
    @Expose
    var bountyClosesDate: Int? = null


    override fun toString(): String {
        return "Question(questionId=$questionId, isAnswered=$isAnswered, viewCount=$viewCount, acceptedAnswerId=$acceptedAnswerId, answerCount=$answerCount, score=$score, lastActivityDate=$lastActivityDate, creationDate=$creationDate, lastEditDate=$lastEditDate, contentLicense=$contentLicense, link=$link, title=$title, bountyAmount=$bountyAmount, bountyClosesDate=$bountyClosesDate)"
    }


}
package com.pradyotprakash.personalblog.core.model.request

import com.pradyotprakash.personalblog.utils.Constants.Keys.CONTENT
import com.pradyotprakash.personalblog.utils.Constants.Keys.CREATED_AT
import com.pradyotprakash.personalblog.utils.Constants.Keys.ID
import com.pradyotprakash.personalblog.utils.Constants.Keys.PUBLICATION_AT
import com.pradyotprakash.personalblog.utils.Constants.Keys.TITLE
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Blog(
    @SerialName(ID) val id: Int? = null,
    @SerialName(CREATED_AT) val createdAt: Instant,
    @SerialName(PUBLICATION_AT) val publicationDate: Instant,
    @SerialName(TITLE) val title: String,
    @SerialName(CONTENT) val content: String,
)

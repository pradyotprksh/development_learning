package com.pradyotprakash.personalblog.core.models.request

import com.pradyotprakash.personalblog.utils.Constants.Keys.CONTENT
import com.pradyotprakash.personalblog.utils.Constants.Keys.ID
import com.pradyotprakash.personalblog.utils.Constants.Keys.PUBLICATION_AT
import com.pradyotprakash.personalblog.utils.Constants.Keys.TITLE
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBlog(
    @SerialName(ID) val id: Int,
    @SerialName(PUBLICATION_AT) val publicationDate: Instant? = null,
    @SerialName(TITLE) val title: String? = null,
    @SerialName(CONTENT) val content: String? = null,
)
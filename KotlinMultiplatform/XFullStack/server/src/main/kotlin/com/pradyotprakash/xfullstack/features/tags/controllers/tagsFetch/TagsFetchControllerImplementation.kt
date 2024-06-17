package com.pradyotprakash.xfullstack.features.tags.controllers.tagsFetch

import com.pradyotprakash.xfullstack.data.tags.TagsDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.UserDetailsNotFound
import core.models.response.TagsResponse
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.XFullStackResponseStatus

class TagsFetchControllerImplementation : TagsFetchController {
    override suspend fun getTrendingTags(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        tagsDataSource: TagsDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val userId = principal?.payload?.getClaim(USER_ID)?.asString()
            ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        // Get top 30 trending tags
        val tags = tagsDataSource.getTags(1, 30)

        val tagsResponse = tags.map { tag ->
            TagsResponse(
                tag = tag.id,
                count = tag.count,
                totalTweets = tweetDataSource.countTweetsWithTag(tag.id)
            )
        }

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.SUCCESS,
                data = tagsResponse,
            )
        )
    }
}
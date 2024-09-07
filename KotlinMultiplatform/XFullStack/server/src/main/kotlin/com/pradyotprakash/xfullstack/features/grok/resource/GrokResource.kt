package com.pradyotprakash.xfullstack.features.grok.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Grok.CHAT
import utils.Constants.Paths.Grok.GROK

@Resource(GROK)
class GrokResource {
    val asdf = 1

    @Resource(CHAT)
    data class ChatResource(
        private val parent: GrokResource = GrokResource(),
    )
}
package com.pradyotprakash.xfullstack.features.grok.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Grok.CHAT
import utils.Constants.Paths.Grok.GROK

@Resource(GROK)
class GrokResource {

    @Resource(CHAT)
    data class Chat(
        private val parent: GrokResource = GrokResource(),
    )
}
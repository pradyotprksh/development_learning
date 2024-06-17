package com.pradyotprakash.xfullstack.features.tags.controllers

import com.pradyotprakash.xfullstack.features.tags.controllers.tagsFetch.TagsFetchController

class TagsController(
    private val tagsFetchController: TagsFetchController,
) : TagsFetchController by tagsFetchController
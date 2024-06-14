package com.pradyotprakash.xfullstack.features.bookmark.controllers

import com.pradyotprakash.xfullstack.features.bookmark.controllers.bookmarkUpdate.BookmarkUpdateController

class BookmarkController(
    private val bookmarkUpdateController: BookmarkUpdateController,
) : BookmarkUpdateController by bookmarkUpdateController
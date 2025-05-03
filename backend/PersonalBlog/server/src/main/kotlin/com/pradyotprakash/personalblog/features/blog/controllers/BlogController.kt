package com.pradyotprakash.personalblog.features.blog.controllers

import com.pradyotprakash.personalblog.features.blog.controllers.add.BlogAddController
import com.pradyotprakash.personalblog.features.blog.controllers.fetch.BlogFetchController
import com.pradyotprakash.personalblog.features.blog.controllers.update.BlogUpdateController

class BlogController(
    private val blogAddController: BlogAddController,
    private val blogFetchController: BlogFetchController,
    private val blogUpdateController: BlogUpdateController,
) : BlogAddController by blogAddController, BlogFetchController by blogFetchController,
    BlogUpdateController by blogUpdateController

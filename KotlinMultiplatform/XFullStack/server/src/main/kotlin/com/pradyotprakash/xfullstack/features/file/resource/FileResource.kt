package com.pradyotprakash.xfullstack.features.file.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.File.FILE
import utils.Constants.Paths.File.UPLOAD

@Resource(FILE)
class FileResource {

    @Resource(UPLOAD)
    data class UploadResource(
        private val parent: FileResource = FileResource(),
    )
}
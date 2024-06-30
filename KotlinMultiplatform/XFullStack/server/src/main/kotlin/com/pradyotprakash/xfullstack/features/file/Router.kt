package com.pradyotprakash.xfullstack.features.file

import com.pradyotprakash.xfullstack.data.file.FileDataSource
import com.pradyotprakash.xfullstack.features.file.controllers.FileController
import com.pradyotprakash.xfullstack.features.file.resource.FileResource
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.file(
    fileController: FileController,
    fileDataSource: FileDataSource,
) {
    post<FileResource.Upload> {
        fileController.uploadFile(
            this.context,
            fileDataSource = fileDataSource,
        )
    }
}
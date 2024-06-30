package com.pradyotprakash.xfullstack.features.file.controllers.upload

import com.pradyotprakash.xfullstack.data.file.FileDataSource
import io.ktor.server.application.ApplicationCall

interface FileUploadController {
    suspend fun uploadFile(
        call: ApplicationCall,
        fileDataSource: FileDataSource,
    )
}
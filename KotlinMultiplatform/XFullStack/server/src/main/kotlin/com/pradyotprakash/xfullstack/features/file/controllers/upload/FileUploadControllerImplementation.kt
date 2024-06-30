package com.pradyotprakash.xfullstack.features.file.controllers.upload

import com.pradyotprakash.xfullstack.data.file.FileDataSource
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import org.bson.types.ObjectId
import utils.Constants.Keys.UPLOAD_BUCKET
import utils.Localization
import utils.XFullStackResponseStatus
import java.io.File

class FileUploadControllerImplementation : FileUploadController {
    override suspend fun uploadFile(
        call: ApplicationCall,
        fileDataSource: FileDataSource,
    ) {
        val multipartData = call.receiveMultipart()

        var uploadBucket: String? = null
        var file: File? = null

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    if (part.name == UPLOAD_BUCKET) {
                        uploadBucket = part.value
                    }
                }

                is PartData.FileItem -> {
                    val fileName = part.originalFileName as String
                    val fileBytes = part.streamProvider().readBytes()
                    file = File("${ObjectId().toHexString()}-$fileName")
                    file?.writeBytes(fileBytes)
                }

                else -> {}
            }
            part.dispose()
        }

        val bucket = uploadBucket
        val uploadFile = file

        if (bucket == null || uploadFile == null) {
            return
        }

        fileDataSource.isBucketPresent(bucket)

        val url = fileDataSource.uploadFile(bucket, uploadFile.name, uploadFile)

        // Delete the file from server storage
        uploadFile.delete()

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.SUCCESS,
                data = url,
            )
        )
    }
}
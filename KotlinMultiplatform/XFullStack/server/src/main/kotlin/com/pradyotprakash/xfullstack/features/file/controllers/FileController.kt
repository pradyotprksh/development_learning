package com.pradyotprakash.xfullstack.features.file.controllers

import com.pradyotprakash.xfullstack.features.file.controllers.upload.FileUploadController

class FileController(
    private val fileUploadController: FileUploadController,
) : FileUploadController by fileUploadController
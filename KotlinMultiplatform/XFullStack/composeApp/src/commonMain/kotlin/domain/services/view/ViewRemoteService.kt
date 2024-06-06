package domain.services.view

import data.request.ViewRequest
import data.response.XFullStackResponse

interface ViewRemoteService {
    suspend fun saveViews(views: List<ViewRequest>): XFullStackResponse<Nothing>
}
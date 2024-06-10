package domain.services.view

import core.models.request.ViewRequest
import core.models.response.XFullStackResponse

interface ViewRemoteService {
    suspend fun saveViews(views: List<ViewRequest>): XFullStackResponse<Nothing>
}
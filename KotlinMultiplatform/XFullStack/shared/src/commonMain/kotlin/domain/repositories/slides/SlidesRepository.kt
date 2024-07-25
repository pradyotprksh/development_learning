package domain.repositories.slides

import data.device.slides.SlidesDetails

interface SlidesRepository {
    suspend fun parseStringToSlides(value: String): SlidesDetails
}
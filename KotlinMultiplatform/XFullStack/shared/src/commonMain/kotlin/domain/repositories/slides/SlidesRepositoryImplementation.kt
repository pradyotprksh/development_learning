package domain.repositories.slides

import data.device.slides.SlidesDetails
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class SlidesRepositoryImplementation : SlidesRepository {
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun parseStringToSlides(value: String): SlidesDetails {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = true
            isLenient = true
        }
        return json.decodeFromString<SlidesDetails>(value)
    }
}
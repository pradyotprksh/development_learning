package data.device.tags

import core.models.realm.TagsDB
import core.models.response.TagsResponse
import core.parser.parseToTagsDB
import domain.services.tags.TagsDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.COUNT

class TagsDBServiceImplementation(
    private val realm: Realm,
) : TagsDBService {
    override suspend fun saveAllTags(tagsResponse: List<TagsResponse>): List<TagsDB> =
        realm.write {
            val unmanagedObject = tagsResponse.map { it.parseToTagsDB() }
            val managedObject = unmanagedObject.map {
                copyToRealm(
                    it, updatePolicy = UpdatePolicy.ALL
                )
            }
            managedObject
        }

    override suspend fun getAllTrendingTags(limit: Int): Flow<ResultsChange<TagsDB>> {
        return realm.query<TagsDB>().sort(
            property = COUNT,
            sortOrder = Sort.DESCENDING,
        ).limit(limit).asFlow()
    }
}
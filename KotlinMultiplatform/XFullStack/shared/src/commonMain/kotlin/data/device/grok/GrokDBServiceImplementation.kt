package data.device.grok

import core.models.realm.GrokChatDB
import domain.services.grok.GrokDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.LAST_MESSAGE_ON

class GrokDBServiceImplementation(
    private val realm: Realm,
) : GrokDBService {
    override fun getAllGrokChats(): Flow<ResultsChange<GrokChatDB>> {
        return realm.query<GrokChatDB>().sort(
            property = LAST_MESSAGE_ON,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }
}
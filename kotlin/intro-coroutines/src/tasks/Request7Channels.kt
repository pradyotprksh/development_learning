package tasks

import contributors.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun loadContributorsChannels(
    service: GitHubService,
    req: RequestData,
    updateResults: suspend (List<User>, completed: Boolean) -> Unit
) {
    coroutineScope {
        val repos = service
            .getOrgRepos(req.org)
            .also { logRepos(req, it) }
            .body() ?: emptyList()

        val channels = Channel<List<User>>()
        for (repo in repos) {
            launch {
                channels.send(
                    service.getRepoContributors(req.org, repo.name).also { logUsers(repo, it) }.bodyList()
                )
            }
        }

        val users = mutableListOf<User>()
        repeat(repos.size) {
            users.addAll(channels.receive())
            updateResults(users.aggregate(), it == repos.lastIndex)
        }
    }
}

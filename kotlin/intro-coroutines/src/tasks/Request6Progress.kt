package tasks

import contributors.*
import java.util.concurrent.atomic.AtomicInteger

suspend fun loadContributorsProgress(
    service: GitHubService,
    req: RequestData,
    updateResults: suspend (List<User>, completed: Boolean) -> Unit
) {
    val repos = service
        .getOrgRepos(req.org)
        .also { logRepos(req, it) }
        .body() ?: emptyList()

    val users = mutableListOf<User>()
    val incrementCounter = AtomicInteger()

    for (repo in repos) {
        val user = service.getRepoContributors(req.org, repo.name).also { logUsers(repo, it) }.bodyList()
        users.addAll(user)
        updateResults(users.aggregate(), incrementCounter.incrementAndGet() == repos.size)
    }
}

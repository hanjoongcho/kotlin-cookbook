package io.github.hanjoongcho.com.squareup.retrofit2

import retrofit2.mock.BehaviorDelegate
import retrofit2.Call
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Collections;
import java.io.IOException
import java.util.concurrent.TimeUnit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import retrofit2.Retrofit



fun main(args: Array<String>) {
    // Create a very simple Retrofit adapter which points the GitHub API.
    val retrofit = Retrofit.Builder()
            .baseUrl(SimpleService.API_URL)
            .build()

    // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.
    val behavior = NetworkBehavior.create()
    val mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(behavior)
            .build()

    val delegate = mockRetrofit.create(GitHub::class.java)
    val gitHub = MockGitHub(delegate)

    // Query for some contributors for a few repositories.
    printContributors(gitHub, "square", "retrofit")
    printContributors(gitHub, "square", "picasso")

    // Using the mock-only methods, add some additional data.
    println("Adding more mock data...\n")
    gitHub.addContributor("square", "retrofit", "Foo Bar", 61)
    gitHub.addContributor("square", "picasso", "Kit Kat", 53)

    // Reduce the delay to make the next calls complete faster.
    behavior.setDelay(500, TimeUnit.MILLISECONDS)

    // Query for the contributors again so we can see the mock data that was added.
    printContributors(gitHub, "square", "retrofit")
    printContributors(gitHub, "square", "picasso")
}

/** A mock implementation of the [GitHub] API interface.  */
internal class MockGitHub(private val delegate: BehaviorDelegate<GitHub>) : GitHub {
    override fun repository(owner: String, repo: String): Call<Project> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun user(owner: String): Call<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val ownerRepoContributors: MutableMap<String, MutableMap<String, MutableList<Contributor>>> = mutableMapOf()

    init {
        // Seed some mock data.
        addContributor("square", "retrofit", "John Doe", 12)
        addContributor("square", "retrofit", "Bob Smith", 2)
        addContributor("square", "retrofit", "Big Bird", 40)
        addContributor("square", "picasso", "Proposition Joe", 39)
        addContributor("square", "picasso", "Keiser Soze", 152)
    }

    override fun contributors(owner: String, repo: String): Call<List<Contributor>> {
        var response: List<Contributor> = Collections.emptyList()
        val repoContributors = ownerRepoContributors[owner]
        if (repoContributors != null) {
            val contributors = repoContributors[repo]
            if (contributors != null) {
                response = contributors
            }
        }
        return delegate.returningResponse(response).contributors(owner, repo)
    }

    fun addContributor(owner: String, repo: String, name: String, contributions: Int) {
        var repoContributors: MutableMap<String, MutableList<Contributor>>? = ownerRepoContributors[owner]
        if (repoContributors == null) {
            repoContributors = LinkedHashMap()
            ownerRepoContributors.put(owner, repoContributors)
        }
        var contributors: MutableList<Contributor>? = repoContributors[repo]
        if (contributors == null) {
            contributors = ArrayList()
            repoContributors.put(repo, contributors)
        }
        contributors.add(Contributor(name, contributions))
    }
}

@Throws(IOException::class)
private fun printContributors(gitHub: GitHub, owner: String, repo: String) {
    println(String.format("== Contributors for %s/%s ==", owner, repo))
    val contributors = gitHub.contributors(owner, repo)
    for ((login, contributions) in contributors.execute().body()!!) {
        println("$login ($contributions)")
    }
    println()
}
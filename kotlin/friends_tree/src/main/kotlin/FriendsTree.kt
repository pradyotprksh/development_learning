import models.Person

class FriendsTree {
    private val users = ArrayList<Person>()

    fun addUser(person: Person) {
        if (person.username.isEmpty()) {
            println("Username can't be empty")
        } else {
            if (searchUser(username = person.username) == null) {
                users.add(person)
            } else {
                println("User with name ${person.username} is already present")
            }
        }
    }

    fun addFriend(username: String, friendUsername: String) {
        val user = searchUser(username = username)
        if (user != null) {
            val friend = searchUser(username = friendUsername)
            if (friend != null) {
                user.friends.add(friend.username)
                friend.friends.add(user.username)
            } else {
                println("No user with username $username was found")
            }
        } else {
            println("No user with username $username was found")
        }
    }

    fun searchUser(username: String): Person? {
        for (user in users) {
            if (user.username == username) {
                return user
            }
        }
        return null
    }

    fun get1stFriends(username: String): List<Person> {
        val friends = ArrayList<Person>()

        val user = searchUser(username = username)
        if (user != null) {
            for (friend in user.friends) {
                searchUser(friend)?.let {
                    friends.add(it)
                }
            }
        } else {
            println("No user with username $username was found")
        }

        return friends
    }

    fun get2ndFriends(username: String): List<Person> {
        val friends = ArrayList<Person>()
        val secondFriendName = HashSet<String>()

        val firstFriends = get1stFriends(username = username)
        for (firstFriend in firstFriends) {
            if (firstFriend.friends.size > 1) {
                secondFriendName.addAll(firstFriend.friends)
            }
        }

        for (secondUser in secondFriendName) {
            if (secondUser == username) {
                continue
            }
            searchUser(username = secondUser)?.let {
                friends.add(it)
            }
        }

        return friends
    }

    fun get3rdFriends(username: String): List<Person> {
        val friends = ArrayList<Person>()
        val thirdFriendName = HashSet<String>()

        val firstFriends = get1stFriends(username = username)
        val secondFriends = get2ndFriends(username = username)
        for (firstFriend in secondFriends) {
            if (firstFriend.friends.size > 1) {
                thirdFriendName.addAll(firstFriend.friends)
            }
        }

        for (thirdUser in thirdFriendName) {
            if (firstFriends.contains(searchUser(thirdUser))) {
                continue
            }
            searchUser(username = thirdUser)?.let {
                friends.add(it)
            }
        }

        return friends
    }

    fun isConnected(firstUsername: String, secondUsername: String): Boolean {
        val firstUser = searchUser(username = firstUsername)
        val secondUser = searchUser(username = secondUsername)

        if (firstUser == null || secondUser == null) {
            println("Asked user is not present")
        } else {
            val friends = HashSet<String>()
            friends.add(firstUsername)

            while (friends.isNotEmpty()) {
                val user = searchUser(friends.first())
                if (user != null) {
                    if (user.friends.contains(secondUsername)) {
                        return true
                    } else {
                        for (friend in user.friends) {
                            if (friend == friends.first()) {
                                continue
                            }
                            friends.addAll(user.friends)
                        }
                    }
                    friends.remove(user.username)
                }
            }
        }

        return false
    }
}
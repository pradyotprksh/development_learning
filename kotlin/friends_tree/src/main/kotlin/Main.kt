import models.Person

fun main() {
    val friendsTree = FriendsTree()

    friendsTree.addUser(Person(name = "Pradyot Prakash", username = "pradyotprksh", emailId = "pradyot@gmail.com"))
    friendsTree.addUser(Person(name = "Ramesh", username = "ramesh4", emailId = "ramesh@gmail.com"))
    friendsTree.addUser(Person(name = "Suresh", username = "suresh", emailId = "ramesh@gmail.com"))
    friendsTree.addUser(Person(name = "Raj", username = "raj1996", emailId = "raj@gmail.com"))
    friendsTree.addUser(Person(name = "Ankit", username = "ankit8", emailId = "ankit@gmail.com"))
    friendsTree.addUser(Person(name = "Sourav", username = "sourav67", emailId = "sourav@gmail.com"))

    friendsTree.addFriend("pradyotprksh", "suresh")
    friendsTree.addFriend("ramesh4", "suresh")
    friendsTree.addFriend("suresh", "sourav67")
    friendsTree.addFriend("suresh", "ankit8")
//    friendsTree.addFriend("raj1996", "ankit8")

    println("Get User Details")
    println(friendsTree.searchUser("pradyotprksh"))
    println(friendsTree.searchUser("ramesh4"))
    println(friendsTree.searchUser("suresh"))
    println(friendsTree.searchUser("raj1996"))
    println(friendsTree.searchUser("ankit8"))
    println(friendsTree.searchUser("sourav67"))

    println("Get 1st Friend List")
    friendsTree.get1stFriends("suresh").forEach { println(it) }

    println("Get 2nd Friend List")
    friendsTree.get2ndFriends("suresh").forEach { println(it) }

    println("Get 3rd Friend List")
    friendsTree.get3rdFriends("suresh").forEach { println(it) }

    println("Is Connected")
    println(friendsTree.isConnected(firstUsername = "pradyotprksh", secondUsername = "suresh"))
    println(friendsTree.isConnected(firstUsername = "pradyotprksh", secondUsername = "raj1996"))
}
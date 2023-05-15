package models

data class Person(
    val name: String,
    val username: String,
    val emailId: String,
    val friends: ArrayList<String> = ArrayList(),
) {
    override fun toString(): String {
        return if (friends.isEmpty()) {
            "$name -> | Username: $username | Email Id: $emailId"
        } else {
            "$name -> | Username: $username | Email Id: $emailId | Friends: $friends"
        }
    }
}

package utils.timeago

internal interface DistancePredicate {
    fun validateDistanceMinutes(distance: Long): Boolean
}
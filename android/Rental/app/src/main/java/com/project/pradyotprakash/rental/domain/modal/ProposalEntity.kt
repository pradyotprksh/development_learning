package com.project.pradyotprakash.rental.domain.modal

import com.github.marlonlom.utilities.timeago.TimeAgo
import com.orhanobut.logger.Logger
import com.project.pradyotprakash.rental.app.utils.ProposalStatus

data class ProposalEntity(
    val _id: String,
    val proposal_id: String,
    val user_id: String,
    val property_id: String,
    val confirm_rent: Boolean,
    val rent_proposal: String,
    val confirm_deposit: Boolean,
    val deposit_proposal: String,
    val confirm_agreements: Boolean,
    val created_on: String,
    val updated_on: String,
    val proposals_created_by_details: UserEntity? = null,
    val proposal_status: String? = null,
) {
    val proposalStatus: ProposalStatus
        get() {
            return when (ProposalStatus.values().find { it.name == proposal_status }) {
                ProposalStatus.Accept -> ProposalStatus.Accept
                ProposalStatus.Reject -> ProposalStatus.Reject
                else -> ProposalStatus.None
            }
        }

    val proposalCreateOnTimeAgo: String
        get() {
            return try {
                TimeAgo.using(created_on.toLong() * 1000)
            } catch (e: Exception) {
                Logger.e(e.toString())
                "*"
            }
        }

    val proposalUpdatedOnTimeAgo: String
        get() {
            return try {
                TimeAgo.using(updated_on.toLong() * 1000)
            } catch (e: Exception) {
                Logger.e(e.toString())
                "*"
            }
        }
}

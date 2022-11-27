package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.data.services.ProposalService

class ProposalRepository(
    private val proposalService: ProposalService,
    private val crashlytics: CrashlyticsService,
) {
    suspend fun createProposal(
        userId: String,
        propertyId: String,
        confirmRent: Boolean,
        rentProposal: String,
        confirmDeposit: Boolean,
        depositProposal: String,
        confirmAgreements: Boolean,
    ) = proposalService.createProposal(
        userId = userId,
        propertyId = propertyId,
        confirmRent = confirmRent,
        rentProposal = rentProposal,
        confirmDeposit = confirmDeposit,
        depositProposal = depositProposal,
        confirmAgreements = confirmAgreements,
    ).parseResponse(crashlytics)
}
package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.ProposalRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProposalUseCase @Inject constructor(
    private val proposalRepository: ProposalRepository,
) {
    suspend fun createProposal(
        userId: String,
        propertyId: String,
        confirmRent: Boolean,
        rentProposal: String,
        confirmDeposit: Boolean,
        depositProposal: String,
        confirmAgreements: Boolean,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            proposalRepository.createProposal(
                userId = userId,
                propertyId = propertyId,
                confirmRent = confirmRent,
                rentProposal = rentProposal,
                confirmDeposit = confirmDeposit,
                depositProposal = depositProposal,
                confirmAgreements = confirmAgreements,
            )
        )
        emit(RenterResponse.Idle)
    }

    suspend fun updateProposal(
        userId: String,
        propertyId: String,
        confirmRent: Boolean,
        rentProposal: String,
        confirmDeposit: Boolean,
        depositProposal: String,
        confirmAgreements: Boolean,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            proposalRepository.updateProposal(
                userId = userId,
                propertyId = propertyId,
                confirmRent = confirmRent,
                rentProposal = rentProposal,
                confirmDeposit = confirmDeposit,
                depositProposal = depositProposal,
                confirmAgreements = confirmAgreements,
            )
        )
        emit(RenterResponse.Idle)
    }
}
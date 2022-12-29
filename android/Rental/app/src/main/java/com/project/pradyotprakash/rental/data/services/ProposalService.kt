package com.project.pradyotprakash.rental.data.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.ProposalEntity
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ProposalService {
    @POST("/renter/property/proposals")
    suspend fun createProposal(
        @Query("user_id") userId: String,
        @Query("property_id") propertyId: String,
        @Query("confirm_rent") confirmRent: Boolean,
        @Query("rent_proposal") rentProposal: String,
        @Query("confirm_deposit") confirmDeposit: Boolean,
        @Query("deposit_proposal") depositProposal: String,
        @Query("confirm_agreements") confirmAgreements: Boolean,
    ): Response<DefaultEntity<ProposalEntity>>

    @PATCH("/renter/property/proposals")
    suspend fun updateProposal(
        @Query("user_id") userId: String,
        @Query("property_id") propertyId: String,
        @Query("confirm_rent") confirmRent: Boolean,
        @Query("rent_proposal") rentProposal: String,
        @Query("confirm_deposit") confirmDeposit: Boolean,
        @Query("deposit_proposal") depositProposal: String,
        @Query("confirm_agreements") confirmAgreements: Boolean,
        @Query("proposal_status") status: String?,
    ): Response<DefaultEntity<ProposalEntity>>

    @DELETE("/renter/property/proposals")
    suspend fun deleteProposal(
        @Query("user_id") userId: String,
        @Query("property_id") propertyId: String,
    ): Response<DefaultEntity<Nothing>>
}
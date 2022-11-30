package com.project.pradyotprakash.rental.app.pages.property.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pradyotprakash.rental.app.composables.ConfirmationDialog
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.navigation.Navigator
import com.project.pradyotprakash.rental.core.navigation.Routes
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.usecase.AuthenticationUseCase
import com.project.pradyotprakash.rental.domain.usecase.PropertyUseCase
import com.project.pradyotprakash.rental.domain.usecase.ProposalUseCase
import com.project.pradyotprakash.rental.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val propertyUseCase: PropertyUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val wishlistUseCase: WishlistUseCase,
    private val localServices: UserLocalServices,
    private val proposalUseCase: ProposalUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val _errorText = MutableLiveData("")
    val error: LiveData<String>
        get() = _errorText
    private val _propertyDetails = MutableLiveData<PropertyEntity?>(null)
    val propertyDetails: LiveData<PropertyEntity?>
        get() = _propertyDetails
    private val _noProperties = MutableLiveData(false)
    val noProperties: LiveData<Boolean>
        get() = _noProperties
    private val _confirmationDialog = MutableLiveData(ConfirmationDialog())
    val confirmationDialog: LiveData<ConfirmationDialog>
        get() = _confirmationDialog
    private val userType: UserType
        get() = localServices.userType
    private val _okayWithRent = MutableLiveData(true)
    val okayWithRent: LiveData<Boolean>
        get() = _okayWithRent
    private val _okayWithDeposit = MutableLiveData(true)
    val okayWithDeposit: LiveData<Boolean>
        get() = _okayWithDeposit
    private val _proposalRent = MutableLiveData("")
    val proposalRent: LiveData<String>
        get() = _proposalRent
    private val _proposalDeposit = MutableLiveData("")
    val proposalDeposit: LiveData<String>
        get() = _proposalDeposit

    fun getPropertyDetails(propertyId: String) {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                propertyUseCase.getProperties(
                    propertyId = propertyId,
                    headerUserId = userId,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> {
                            updateErrorState(it.exception.message)
                            _noProperties.value = true
                        }
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {
                            val data = it.data.data
                            data?.firstOrNull()?.let { propertyDetails ->
                                _noProperties.value = false
                                _propertyDetails.value = propertyDetails

                                _okayWithRent.value =
                                    propertyDetails.proposal_details?.confirm_rent ?: true
                                _okayWithDeposit.value =
                                    propertyDetails.proposal_details?.confirm_deposit ?: true
                                _proposalRent.value =
                                    propertyDetails.proposal_details?.rent_proposal ?: ""
                                _proposalDeposit.value =
                                    propertyDetails.proposal_details?.deposit_proposal ?: ""
                            } ?: kotlin.run {
                                _noProperties.value = true
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Navigate back
     */
    fun navigateBack() = navigator.navigateBack()

    fun updateErrorState(errorText: String? = null) {
        _loading.value = false
        _errorText.value = errorText ?: ""
    }

    fun isPropertyOwner(propertyCreatedBy: String?): Boolean {
        if (propertyCreatedBy != null && authenticationUseCase.getCurrentUserId() != null) {
            return authenticationUseCase.getCurrentUserId() == propertyCreatedBy
        }
        return false
    }

    fun searchForRenter() {
        navigator.navigate {
            it.navigate("${Routes.Search.route}${false}/${UserType.Renter.name}")
        }
    }

    fun goToPropertyEdit(propertyId: String) {
        navigator.navigate {
            it.navigate("${Routes.Property.route}$propertyId")
        }
    }

    fun confirmAddToWishList(propertyId: String, propertyName: String) {
        _confirmationDialog.value = ConfirmationDialog(
            text = String.format(TR.confirmAddToWishlistProperty, propertyName),
            onConfirm = {
                _confirmationDialog.value = ConfirmationDialog()
                addPropertyToWishlist(propertyId)
            },
            onDismiss = {
                _confirmationDialog.value = ConfirmationDialog()
            },
        )
    }

    private fun addPropertyToWishlist(propertyId: String) {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                wishlistUseCase.createWishlist(
                    userId = userId,
                    propertyId = propertyId,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> {}
                    }
                }
            }
        }
    }

    fun showWishListOption(): Boolean = userType != UserType.Renter ||
            _propertyDetails.value?.is_in_wishlist != true

    fun okayWithRent(value: Boolean) {
        _okayWithRent.value = value
        if (value) {
            proposalRent("")
        }
    }

    fun okayWithDeposit(value: Boolean) {
        _okayWithDeposit.value = value
        if (value) {
            proposalDeposit("")
        }
    }

    fun proposalRent(value: String) {
        _proposalRent.value = value
    }

    fun proposalDeposit(value: String) {
        _proposalDeposit.value = value
    }

    fun validateProposal(propertyId: String, closeAction: () -> Unit) {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                if (_propertyDetails.value?.proposal_details == null) {
                    proposalUseCase.createProposal(
                        userId = userId,
                        propertyId = propertyId,
                        confirmRent = _okayWithRent.value ?: true,
                        confirmDeposit = _okayWithDeposit.value ?: true,
                        rentProposal = _proposalRent.value ?: "",
                        depositProposal = _proposalDeposit.value ?: "",
                        confirmAgreements = true,
                    )
                } else {
                    proposalUseCase.updateProposal(
                        userId = userId,
                        propertyId = propertyId,
                        confirmRent = _okayWithRent.value ?: true,
                        confirmDeposit = _okayWithDeposit.value ?: true,
                        rentProposal = _proposalRent.value ?: "",
                        depositProposal = _proposalDeposit.value ?: "",
                        confirmAgreements = true,
                    )
                }.collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> closeAction()
                    }
                }
            }
        }
    }

    fun confirmDeleteProposal(propertyId: String, propertyName: String, closeAction: () -> Unit) {
        _confirmationDialog.value = ConfirmationDialog(
            text = String.format(TR.confirmDeleteProposalProperty, propertyName),
            onConfirm = {
                _confirmationDialog.value = ConfirmationDialog()
                deletePropertyProposal(propertyId, closeAction)
            },
            onDismiss = {
                _confirmationDialog.value = ConfirmationDialog()
            },
        )
    }

    private fun deletePropertyProposal(propertyId: String, closeAction: () -> Unit) {
        viewModelScope.launch {
            authenticationUseCase.getCurrentUserId()?.let { userId ->
                proposalUseCase.deleteProposal(
                    userId = userId,
                    propertyId = propertyId,
                ).collect {
                    when (it) {
                        is RenterResponse.Error -> updateErrorState(it.exception.message)
                        is RenterResponse.Idle -> _loading.value = false
                        is RenterResponse.Loading -> _loading.value = true
                        is RenterResponse.Success -> closeAction()
                    }
                }
            }
        }
    }
}
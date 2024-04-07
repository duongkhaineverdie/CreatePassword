package com.thucthantinh.createpassword.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thucthantinh.createpassword.domain.interactors.GetPasswordsUseCase
import com.thucthantinh.createpassword.domain.interactors.SavePasswordsUseCase
import com.thucthantinh.createpassword.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val getPasswordsUseCase: GetPasswordsUseCase,
    val savePasswordsUseCase: SavePasswordsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getPasswords()
    }

    private fun getPasswords() {
        viewModelScope.launch {
            getPasswordsUseCase(Unit).collectLatest {
                it.onSuccess { passwords ->
                    _uiState.update { state ->
                        state.copy(
                            passwords = passwords.toMutableList()
                        )
                    }
                }
            }
        }
    }

    fun onValueChangeLength(value: String) {
        _uiState.update {
            it.copy(
                passwordLength = value.toIntOrNull()
            )
        }
    }

    fun actionIncludeLowercase(boolean: Boolean) {
        if (onOneIncludeSelected())
            _uiState.update {
                it.copy(includeLowercase = true)
            }
        else
            _uiState.update {
                it.copy(includeLowercase = boolean)
            }
    }

    fun actionIncludeUppercase(boolean: Boolean) {
        if (onOneIncludeSelected())
            _uiState.update {
                it.copy(includeUppercase = true)
            }
        else
            _uiState.update {
                it.copy(includeUppercase = boolean)
            }
    }

    fun actionIncludeDigits(boolean: Boolean) {
        if (onOneIncludeSelected())
            _uiState.update {
                it.copy(includeDigits = true)
            }
        else
            _uiState.update {
                it.copy(includeDigits = boolean)
            }
    }

    fun actionIncludeSpecialChars(boolean: Boolean) {
        if (onOneIncludeSelected())
            _uiState.update {
                it.copy(includeSpecialChars = true)
            }
        else
            _uiState.update {
                it.copy(includeSpecialChars = boolean)
            }
    }

    fun generatePassword() {
        uiState.value.passwordLength?.let {
            if (it in 6..16) {
                val characterPool = buildString {
                    if (uiState.value.includeLowercase) append(Constants.LOWERCASE_LETTERS)
                    if (uiState.value.includeUppercase) append(Constants.UPPERCASE_LETTERS)
                    if (uiState.value.includeDigits) append(Constants.DIGITS)
                    if (uiState.value.includeSpecialChars) append(Constants.SPECIAL_CHAR)
                }

                // Generate random password using string concatenation
                val generatedPassword = (1..it)
                    .map { _ ->
                        characterPool.random()
                    }
                    .joinToString("")

                _uiState.update { state ->
                    state.copy(passwordGenerated = generatedPassword, isErrorInput = false)
                }
            } else {
                _uiState.update { state ->
                    state.copy(isErrorInput = true)
                }
            }
        } ?: run {
            _uiState.update { state ->
                state.copy(isErrorInput = true)
            }
        }
    }

    private fun onOneIncludeSelected(): Boolean {
        val countTrue = listOf(
            uiState.value.includeLowercase,
            uiState.value.includeUppercase,
            uiState.value.includeDigits,
            uiState.value.includeSpecialChars
        ).count { it }
        return countTrue == 1
    }

    fun savePassword(password: String) {
        val oldPasswords = uiState.value.passwords
        if (!oldPasswords.contains(password)) {
            val newPasswords = oldPasswords + password
            viewModelScope.launch {
                savePasswordsUseCase(newPasswords.toSet()).onSuccess {
                    getPasswords()
                }
            }
        }
    }
}

data class HomeUiState(
    val passwordLength: Int? = null,
    val includeLowercase: Boolean = true,
    val includeUppercase: Boolean = true,
    val includeDigits: Boolean = true,
    val includeSpecialChars: Boolean = true,
    val passwordGenerated: String = "",
    val isErrorInput: Boolean = false,
    val passwords: MutableList<String> = arrayListOf()
)
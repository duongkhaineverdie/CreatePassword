package com.emenike.createpassword.domain.interactors

import com.emenike.createpassword.data.repository.IRepository
import com.emenike.createpassword.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SavePasswordsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Set<String>, Unit>(dispatcher) {
    override suspend fun block(param: Set<String>): Unit = repository.savePasswords(param)
}
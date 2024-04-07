package com.thucthantinh.createpassword.domain.interactors

import com.thucthantinh.createpassword.data.repository.IRepository
import com.thucthantinh.createpassword.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SavePasswordsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Set<String>, Unit>(dispatcher) {
    override suspend fun block(param: Set<String>): Unit = repository.savePasswords(param)
}
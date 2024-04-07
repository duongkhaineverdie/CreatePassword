package com.thucthantinh.createpassword.domain.interactors

import com.thucthantinh.createpassword.data.repository.IRepository
import com.thucthantinh.createpassword.domain.interactors.type.BaseUseCaseFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetPasswordsUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit, Set<String>>(dispatcher) {
    override suspend fun build(param: Unit): Flow<Set<String>> = repository.getPasswords()

}
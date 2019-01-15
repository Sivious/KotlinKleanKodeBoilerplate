package com.sivianes.kotlinkleankode.domain.interactor

import com.sivianes.kotlinkleankode.data.UserRepository

class UserInteractorImpl(private val userRepository: UserRepository) : UserInteractor {
    override fun getUserId() = userRepository.giveUserId()
}
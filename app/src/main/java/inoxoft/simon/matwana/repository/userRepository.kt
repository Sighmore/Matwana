package inoxoft.simon.matwana.repository

import inoxoft.simon.matwana.model.auth.Database
import inoxoft.simon.matwana.model.auth.UserDetails

class UserRepository(private val db: Database){

    suspend fun upsertUser(user: UserDetails){
        db.dao.upsertUser(user)
    }

    suspend fun deleteUser(user: UserDetails){
        db.dao.deleteUser(user)
    }

    fun getUser()= db.dao.getUser()

}
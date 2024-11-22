package inoxoft.simon.matwana.model.auth

import android.icu.text.MessagePattern.ArgType.SELECT
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: UserDetails)

    @Delete
    suspend fun deleteUser(user: UserDetails)

    @Query("SELECT * FROM user_details")
    fun getUser(): UserDetails
}
package inoxoft.simon.matwana.model.auth

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDetails::class], version = 1)
abstract class Database : RoomDatabase(){
    abstract val dao: UserDao
}
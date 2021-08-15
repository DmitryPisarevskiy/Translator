package geekbrains.ru.translator.room

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM MeaningsEntity WHERE word LIKE :word")
    suspend fun getMeaningsByWord(word: String): List<MeaningsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: MeaningsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<MeaningsEntity>)

    @Update
    suspend fun update(entity: MeaningsEntity)

    @Delete
    suspend fun delete(entity: MeaningsEntity)
}
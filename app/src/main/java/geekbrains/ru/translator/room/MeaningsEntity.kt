package geekbrains.ru.translator.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MeaningsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val imageURL: String?,
    val translation: String?
)
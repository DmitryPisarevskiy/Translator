package geekbrains.ru.translator.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import geekbrains.ru.translator.model.data.Meanings

@Entity
class HistoryEntity (
    @PrimaryKey
    @ColumnInfo (name = "word")
    val word: String,

    @ColumnInfo (name = "meanings")
    val meanings: List<Meanings>
)

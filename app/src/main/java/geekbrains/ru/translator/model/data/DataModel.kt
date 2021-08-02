package geekbrains.ru.translator.model.data

import com.google.gson.annotations.Expose

class DataModel(
    @Expose val text: String?,
    @Expose val meanings: List<Meanings>?
)

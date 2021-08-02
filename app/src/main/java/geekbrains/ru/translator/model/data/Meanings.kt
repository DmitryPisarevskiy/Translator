package geekbrains.ru.translator.model.data

import com.google.gson.annotations.Expose

class Meanings(
    @Expose val translation: Translation?,
    @Expose val imageUrl: String?
)

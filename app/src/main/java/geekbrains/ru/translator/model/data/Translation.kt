package geekbrains.ru.translator.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Translation(@Expose @SerializedName("text") val translation: String?)

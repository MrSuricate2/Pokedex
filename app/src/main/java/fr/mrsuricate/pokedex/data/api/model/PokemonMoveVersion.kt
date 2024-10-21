package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonMoveVersion(
    @SerializedName("level_learned_at") var levelLearnedAt: Int? = null,
    @SerializedName("move_learn_method") var moveLearnMethod: NamedApiResource? = null,
    @SerializedName("version_group") var versionGroup: NamedApiResource? = null
) : Parcelable
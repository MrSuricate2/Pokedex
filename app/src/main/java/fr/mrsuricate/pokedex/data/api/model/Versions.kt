package fr.mrsuricate.pokedex.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Versions(
    @SerializedName("generation-i") var generationI: GenerationI? = GenerationI(),
    @SerializedName("generation-ii") var generationII: GenerationII? = GenerationII(),
    @SerializedName("generation-iii") var generationIII: GenerationIII? = GenerationIII(),
    @SerializedName("generation-iv") var generationIVv: GenerationIV? = GenerationIV(),
    @SerializedName("generation-v") var generationV: GenerationV? = GenerationV(),
    @SerializedName("generation-vi") var generationVI: GenerationVI? = GenerationVI(),
    @SerializedName("generation-vii") var generationVII: GenerationVII? = GenerationVII(),
    @SerializedName("generation-viii") var generationVIII: GenerationVIII? = GenerationVIII()
) : Parcelable
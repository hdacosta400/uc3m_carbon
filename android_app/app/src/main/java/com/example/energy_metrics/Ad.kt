package com.example.energy_metrics

import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("html")
    val html: String
)

data class EnergyDeltas(
    @SerializedName("energyDeltas")
    val energyDeltas: List<Double>
)
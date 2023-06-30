package com.example.energy_metrics

import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("ad_html")
    val ad_html: String?
)

data class EnergyDeltas(
    @SerializedName("energyDeltas")
    val energyDeltas: List<Double>
)
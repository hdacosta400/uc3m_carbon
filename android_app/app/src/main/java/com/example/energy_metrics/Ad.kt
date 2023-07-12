package com.example.energy_metrics

import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("ad_html")
    val ad_html: String?,
    @SerializedName("campaign_info")
    val campaign_info: CampaignInfo
)

data class CampaignInfo(
    @SerializedName("source")
    val source: String?,
    @SerializedName("campaign_id")
    val campaign_id: String?,
    @SerializedName("impression_id")
    val impression_id: String?,
    @SerializedName("ad_id")
    val ad_id: String?,
    @SerializedName("iteration")
    val iteration: Int?,
    @SerializedName("_id")
    val _id: String?
)

data class EnergyDeltas(
    @SerializedName("campaign_infos")
    val campaign_infos: List<CampaignInfo>,
    @SerializedName("baseline_energy_values")
    val baseline_energy_values: List<Double>,
    @SerializedName("delta_energy_values")
    val delta_energy_values: List<Double>

)
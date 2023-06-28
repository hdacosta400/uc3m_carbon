package com.example.energy_metrics

import android.content.Context
import android.system.Os
import android.system.OsConstants
import android.os.SystemClock
import android.webkit.WebView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



/**
 Process from medium article: https://eng.lyft.com/monitoring-cpu-performance-of-lyfts-android-applications-4e36fafffe12
 processTimeSec = uptimeSec - (starttime / clockSpeedHz)
 cpuTimeSec = (time elapsed) / clockSpeedHz
 avgUsagePercent' = 100 * (cpuTimeSec / processTimeSec)
 avgUsagePercent = avgUsagePercent' / numCores

 relative cpu usage % (measure cpu usage btw two points in time)

 cpuTimeDeltaSec = cpuTimeSec2 - cpuTimeSec1
 processTimeDeltaSec = processTimeSec2 - processTimeSec1

 relAvgUsagePercent' = 100 * (cpuTimeDeltaSec / processTimeDeltaSec)
 relAvgUsagePercent = relAvgUsagePercent' / numCores

 **/

const val BASE_URL = "/"
class EnergyProfiler(context: MainActivity) {
    init {
        var context = context
    }
    val WATTAGE_CONSTANT = 10
    val numCores = Os.sysconf(OsConstants._SC_NPROCESSORS_CONF)
    val uptimeSec = SystemClock.elapsedRealtime() / 1000.0
    val clockSpeedHz = Os.sysconf(OsConstants._SC_CLK_TCK)
    var energy_delta = 0.0 // for temp display on screen
    var webView = WebView(context)


    fun getAdStrings(): List<String> {
        val retroFitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL) // base url for server
            .build()
            .create(AdsServerAPI::class.java)

        val retrofitData = retroFitBuilder.getAds()
        var adHtmls = ArrayList<String>();
        retrofitData.enqueue(object : Callback<List<Ad>?> {
            override fun onResponse(call: Call<List<Ad>?>, response: Response<List<Ad>?>) {
                val responseBody = response.body()!!
                for (ad in responseBody) {
                    adHtmls.add(ad.html)
                }
            }
            override fun onFailure(call: Call<List<Ad>?>, t: Throwable) {
                System.out.println("Error getting ads from API!")
            }
        })

        return adHtmls;
    }

    fun postEnergyDeltas(energyDeltas: List<Double>) {
        val retroFitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL) // base url for server
            .build()
            .create(AdsServerAPI::class.java)


        val retrofitPost = retroFitBuilder.postEnergyDeltas(EnergyDeltas(energyDeltas));
        retrofitPost!!.enqueue(object : Callback<EnergyDeltas?> {
            override fun onResponse(call: Call<EnergyDeltas?>, response: Response<EnergyDeltas?>) {
                System.out.println("successfully POSTed deltas to ad server")
            }

            override fun onFailure(call: Call<EnergyDeltas?>, t: Throwable) {
                System.out.println("not able to POST data to ad server")
            }
        })
    }


    fun readEnergy() {
        /**
         * GETs list of ads to render, calculates their energies, and POSTs them back to server
         */
        val trials = 10

        var adHtmls = getAdStrings();
        var energyDeltas = ArrayList<Double>()

        for (adHtml in adHtmls) {
            var relativeAvg = 0.0
            for (i in 1..trials+1) {
                relativeAvg += calcRelativeAvgUsagePercentage("")
            }
            energy_delta = relativeAvg / trials * WATTAGE_CONSTANT
            energyDeltas.add(energy_delta)
        }

        postEnergyDeltas(energyDeltas)
    }


    fun renderAd(adHtml: String) {
        webView.loadData(adHtml, "text/html", "UTF-8")
    }

    fun calcRelativeAvgUsagePercentage(adHtml: String): Double {


        val startTime1 = System.currentTimeMillis() / 1000.0
        renderAd("<html></html>") // render blank

        val processTimeSec1 = uptimeSec - (startTime1 / clockSpeedHz)
        val timeElapsed1 = (System.currentTimeMillis() - startTime1) / 1000.0
        val cpuTimeSec1 = (timeElapsed1) / clockSpeedHz

        val startTime2 = System.currentTimeMillis() / 1000.0
        renderAd(adHtml) // render ad of interest

        val processTimeSec2 = uptimeSec - (startTime2 / clockSpeedHz)
        val timeElapsed2 = (System.currentTimeMillis() - startTime2) / 1000.0
        val cpuTimeSec2 = (timeElapsed2) / clockSpeedHz


        val cpuTimeDeltaSec = cpuTimeSec2 - cpuTimeSec1
        val processTimeDeltaSec = processTimeSec2 - processTimeSec1

        var relAvgUsagePercent = 100 * (cpuTimeDeltaSec / processTimeDeltaSec)
        relAvgUsagePercent /= numCores

        return relAvgUsagePercent
    }
}
//
//  usage_metrics.swift
//  energy_metrics
//
//  Created by Howard DaCosta on 6/21/23.
//

import Foundation
import SwiftUI
import WebKit



var ENERGY_CONSUMPTION = 7.0 // wattage consumption heuristic for iOS processors
class EnergyMetrics {
    
    
    var webView = WKWebView()
    var campaign_infos: [CampaignInfo] = []
    var baselines: [Double] = []
    var deltas: [Double] = []
    
    
    func resetWebView() {
        webView.loadHTMLString(blankHTML, baseURL: nil)
    }
    
    func renderURL(htmlString: String) {
        webView.loadHTMLString(htmlString, baseURL: nil)
    }
    
    
    func eval(html: String) -> Double{
        
        var avg_utilization = 0.0
        let trials = 10
        
        for i in 1 ... trials+1 {
            if (i != 1) { // skip first trial since it has high startup
                var t = clock()
                t = clock() - t
                let cpu_time = Double(t) / Double(CLOCKS_PER_SEC)
                let start = Date()
                renderURL(htmlString: html)
                let cpu_utilization = (cpu_time / -start.timeIntervalSinceNow) * 100.0
//                print("The function takes \(t) ticks, which is \(cpu_time) seconds of CPU time")
//                print("Elapsed time: \(-start.timeIntervalSinceNow) seconds")
//                print("Approximate CPU Utilization: \(cpu_utilization) %")
                avg_utilization += cpu_utilization
            }
        }
        
        return (avg_utilization / Double(trials))
    }
    
    
    func eval_render_delta(renderHTML: String) -> Double {
        // need to pull renderHTML from something
        var _ = generate_energy_deltas();
        return 0.0;
    }
    
    
    func generate_energy_deltas() -> Double {
        print("getting in here")
//            getAdStrings(completionHandler: completionHandler);

        getAdStrings()
//        func completionHandler() -> Void {
        for ad in adsToRender {
            let campaign_info = ad.campaign_info
            print("campaign_info:\(campaign_info)")
            let energy_baseline = eval(html: blankHTML) * ENERGY_CONSUMPTION
            let energy_delta = (eval(html: ad.ad_html) * ENERGY_CONSUMPTION) - energy_baseline
            print("baseline, edelt:\(energy_baseline), \(energy_delta)")
            campaign_infos.append(campaign_info)
            deltas.append(energy_delta)
            baselines.append(energy_baseline)
        }
        postDeltas(campaign_infos: campaign_infos, deltas: deltas, baselines: baselines)
//        }
        
        return 0.0x
    }

}

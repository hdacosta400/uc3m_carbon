//
//  usage_metrics.swift
//  energy_metrics
//
//  Created by Howard DaCosta on 6/21/23.
//

import Foundation
import SwiftUI
import WebKit

class EnergyMetrics {
    
    func renderURL(htmlString: String) {
        let webView1 = WKWebView()
        webView1.loadHTMLString(htmlString, baseURL: nil)
    }
    
    
    func eval(html: String) -> Double{
        
        var avg_utilization = 0.0
        let trials = 10
        
        for i in 1 ... trials {
            if (i != 1) { // skip first trial since it has high startup
                var t = clock()
                t = clock() - t
                let cpu_time = Double(t) / Double(CLOCKS_PER_SEC)
                let start = Date()
                renderURL(htmlString: html)
                let cpu_utilization = (cpu_time / -start.timeIntervalSinceNow) * 100.0
                print("The function takes \(t) ticks, which is \(cpu_time) seconds of CPU time")
                print("Elapsed time: \(-start.timeIntervalSinceNow) seconds")
                print("Approximate CPU Utilization: \(cpu_utilization) %")
                avg_utilization += cpu_utilization
            }

        }
        
        return avg_utilization / Double(trials)
    }
    
    
    func eval_render_delta(renderHTML: String) -> Double {
        // need to pull renderHTML from something
        return eval(html: renderHTML) - eval(html: blankHTML);
    }

}

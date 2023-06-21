/*
See the License.txt file for this sample’s licensing information.
*/

import SwiftUI

    
struct ContentView: View {
    var e: EnergyMetrics;
    var body: some View {
        VStack {
//            Text("blank util: \(e.eval(html:blankHTML))")
//            Text("render util: \(e.eval(html:exampleHTML))")
            Text("CPU Utilization: \(e.eval_render_delta(renderHTML: exampleHTML)) %")
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(e: EnergyMetrics())
    }
}

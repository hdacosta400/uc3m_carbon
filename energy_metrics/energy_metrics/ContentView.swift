/*
See the License.txt file for this sample’s licensing information.
*/

import SwiftUI

    
struct ContentView: View {
    var e = EnergyMetrics()
    var body: some View {
        VStack {
            Text("CPU Power Consumption: \(e.eval_render_delta(renderHTML: exampleHTML)) W")
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(e: EnergyMetrics())
    }
}

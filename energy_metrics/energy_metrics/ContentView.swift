/*
See the License.txt file for this sample’s licensing information.
*/

import SwiftUI

    
struct ContentView: View {
    var e: EnergyMetrics;
    var body: some View {
        VStack {
            Text("CPU Utilization: \(e.eval(fullURL:"")) %")
            
//            Link(destination: URL(string: "https://www.apple.com")!) {
//                Image(systemName: "link.circle.fill")
//                    .font(.largeTitle)
//            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(e: EnergyMetrics())
    }
}

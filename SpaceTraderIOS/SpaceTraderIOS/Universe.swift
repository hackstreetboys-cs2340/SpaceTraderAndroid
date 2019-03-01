//
//  Universe.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/21/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Universe: CustomStringConvertible {
    var solarSystems: [SolarSystem] = []
    
    var description: String {
        var str = "Universe (\(solarSystems.count) unique systems):"
        for system in solarSystems {
            str = "\(str)\n\(system)\n"
        }
        return str
    }
}

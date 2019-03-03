//
//  SolarSystem.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/21/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class SolarSystem: CustomStringConvertible {
    var planets: [Planet]
    var name: String
    var coordinates: Coordinates
    
    var description: String {
        var str = "System Name: \(name)\nCoordinates: \(coordinates)\nPlanets:\n"
        for planet in planets {
            str = "\(str)\(planet)\n"
        }
        return str
    }
    
    convenience init() {
        self.init(planets: [], name: "", coordinates: Coordinates())
    }
    
    init(planets: [Planet], name: String, coordinates: Coordinates) {
        self.planets = planets
        self.name = name
        self.coordinates = coordinates
    }
    
    
}

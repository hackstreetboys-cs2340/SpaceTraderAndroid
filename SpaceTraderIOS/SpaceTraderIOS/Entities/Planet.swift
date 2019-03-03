//
//  Planet.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/21/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Planet: CustomStringConvertible {
    var name: String
    var techLevel: TechLevel
    var resources: ResourceType
    var solarSystem: SolarSystem
    
    var description: String {
        return "\tName: \(name)\n\tTech Level: \(techLevel)\n\tResources: \(resources)\n\tSolar System: \(solarSystem.name)\n"
    }
    
    convenience init() {
        self.init(name: "", techLevel: .PreAgriculture, resources: .NoSpecialResources, solarSystem: SolarSystem())
    }
    
    init(name: String, techLevel: TechLevel, resources: ResourceType, solarSystem: SolarSystem) {
        self.name = name
        self.techLevel = techLevel
        self.resources = resources
        self.solarSystem = solarSystem
    }
}

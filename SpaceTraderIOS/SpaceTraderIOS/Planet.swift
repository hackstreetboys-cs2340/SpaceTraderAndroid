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

enum TechLevel: Int, CaseIterable {
    case PreAgriculture = 0
    case Agriculture = 1
    case Medieval = 2
    case Renaissance = 3
    case EarlyIndustrial = 4
    case Industrial = 5
    case PostIndustrial = 6
    case HiTech = 7
}

enum ResourceType: Int, CaseIterable {
    case NoSpecialResources = 0
    case MineralRich = 1
    case MineralPoor = 2
    case Desert = 3
    case LotsOfWater = 4
    case RichSoil = 5
    case PoorSoil = 6
    case RichFauna = 7
    case Lifeless = 8
    case WeirdMushrooms = 9
    case LotsOfHerbs = 10
    case Artistic = 11
    case Warlike = 12
}

//
//  UniverseGenerator.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/1/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class UniverseGenerator {
    private static let maxPlanetsPerSolarSystem = 10
    
    @available (*, unavailable) init() {}
    
    static func generate(using generator: inout SeededGenerator, success: @escaping (_ universe: Universe) -> Void, fail: @escaping (_ error: Error) -> Void) {
        let universe = Universe()
        if let path = Bundle.main.path(forResource: "Constants", ofType: "json") {
            do {
                // get the list of planet names from the "Constants.json" file
                let data = try Data(contentsOf: URL(fileURLWithPath: path))
                let json = try JSONSerialization.jsonObject(with: data, options: .mutableLeaves) as? [String : Any]
                guard var planetNames = json?["PlanetNames"] as? [String] else {return}
                // 10 to 40 solar systems in a universe
                var numSystems = Int.random(in: 10 ..< 40, using: &generator)
                var usedCoordinates: [Coordinates] = []
                // Make each solar system
                for index in 0 ..< numSystems - 1 {
                    let coords = generateCoordinates(without: usedCoordinates, using: &generator)
                    usedCoordinates.append(coords)
                    let solarSystem = SolarSystem()
                    solarSystem.name = "System \(index)"
                    solarSystem.coordinates = coords
                    var numPlanets = 0
                    
                    // 1 to 10 planets in a solar system
                    if planetNames.count >= 10 {
                        numPlanets = Int.random(in: 1 ..< 10, using: &generator)
                    } else {
                        // unless there are less than 10 planets left
                        numPlanets = numPlanets == 1 ? 1 : Int.random(in: 1 ..< planetNames.count, using: &generator)
                    }
                    
                    // If there will be 0 planet names left
                    if (planetNames.count - numPlanets == 0) {
                        numSystems = index
                    }
                    
                    // make a planet
                    for _ in 0 ..< numPlanets {
                        let planetName = planetNames.randomElement(using: &generator)!
                        planetNames.removeAll { $0 == planetName }
                        
                        let planet = makePlanet(name: planetName, system: solarSystem, using: &generator)
                        solarSystem.planets.append(planet)
                    }
                    universe.solarSystems.append(solarSystem)
                }
                success(universe)
            } catch {
                print(error)
                fail(error)
            }
        }
    }
    
    private static func makePlanet(name: String, system: SolarSystem, using generator: inout SeededGenerator) -> Planet {
        var resources = ResourceType.NoSpecialResources
        // 25% chance of randomly choosing a resource type
        // 75% chance of being a guaranteed NoSpecialResources
        if Int.random(in: 0 ..< 100, using: &generator) < 25 {
            resources = ResourceType(rawValue: Int.random(in: 0 ..< ResourceType.allCases.count, using: &generator))!
        }
        let techLevel = TechLevel(rawValue: Int.random(in: 0 ..< TechLevel.allCases.count, using: &generator))!
        let planet = Planet(name: name, techLevel: techLevel, resources: resources, solarSystem: system)
        return planet
    }
    
    private static func generateCoordinates(without coordinates: [Coordinates], using generator: inout SeededGenerator) -> Coordinates {
        var coords = Coordinates()
        var validCoords = false
        var count = 0
        while !validCoords {
            if count == 1000 { break }
            coords = Coordinates(latitude: Int.random(in: 0 ..< 100, using: &generator), longitude: Int.random(in: 0 ..< 150, using: &generator))
            validCoords = !coordinates.contains(where: { (badCoord) -> Bool in
                return coords.latitude == badCoord.latitude && coords.longitude == badCoord.longitude
            })
            if validCoords { return coords }
            count += 1
        }
        print("There are probably no more possible coordinates without a duplicate...")
        return Coordinates()
    }
}

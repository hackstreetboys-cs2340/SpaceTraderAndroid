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
    
    func generate(with generator: inout SeededGenerator, success: @escaping () -> Void, fail: @escaping (_ error: Error) -> Void) {
        // 10 to 40 solar systems in a universe
        //let numSystems = Int.random(in: 10 ..< 40)
        let numSystems = Int.random(in: 10 ..< 40, using: &generator)
        if let path = Bundle.main.path(forResource: "Constants", ofType: "json") {
            do {
                // get the list of planet names from the "Constants.json" file
                let data = try Data(contentsOf: URL(fileURLWithPath: path))
                let json = try JSONSerialization.jsonObject(with: data, options: .mutableLeaves) as? [String : Any]
                guard var planetNames = json?["PlanetNames"] as? [String] else {return}
                var usedCoordinates: [Coordinates] = []
                // Make each solar system
                for index in 0 ..< numSystems - 1 {
                    let coords = generateCoordinates(without: usedCoordinates, using: &generator)
                    usedCoordinates.append(coords)
                    let solarSystem = SolarSystem()
                    solarSystem.name = "System \(index)"
                    solarSystem.coordinates = coords
                    var numPlanets = 0
                    // 0 to 10 planets in a solar system
                    if planetNames.count >= 10 {
                        numPlanets = Int.random(in: 0 ..< 10, using: &generator)
                    } else {
                        numPlanets = Int.random(in: 0 ..< planetNames.count, using: &generator)
                    }
                    // make a planet
                    for _ in 0 ..< numPlanets {
                        let planetName = planetNames.randomElement(using: &generator)!
                        planetNames.removeAll { $0 == planetName }
                        
                        let planet = makePlanet(name: planetName, system: solarSystem, using: &generator)
                        solarSystem.planets.append(planet)
                    }
                    solarSystems.append(solarSystem)
                }
                success()
            } catch {
                print(error)
                fail(error)
            }
        }
    }
    
    private func makePlanet(name: String, system: SolarSystem, using generator: inout SeededGenerator) -> Planet {
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
    
    private func generateCoordinates(without coordinates: [Coordinates], using generator: inout SeededGenerator) -> Coordinates {
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

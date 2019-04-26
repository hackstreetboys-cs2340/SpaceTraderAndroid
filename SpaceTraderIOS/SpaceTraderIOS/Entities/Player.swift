//
//  Player.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/20/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import FirebaseDatabase

class Player: CustomStringConvertible {
    var name: String
    var pilotSkill: Int
    var engineSkill: Int
    var tradeSkill: Int
    var fightSkill: Int
    var ship: Ship
    var difficulty: Difficulty
    var seed: UInt64
    var wallet: Int
    var location: Planet?
    
    var description: String {return "Name: \(name)\nPilot Skill: \(pilotSkill)\nEngine Skill: \(engineSkill)\nTrade Skill: \(tradeSkill)\nFight Skill: \(fightSkill)\nShip Type: \(ship)\nDifficulty: \(difficulty)"}
    
    convenience init() {
        self.init(name: "", pilotSkill: 0, engineSkill: 0, tradeSkill: 0, fightSkill: 0, difficulty: .Easy, seed: 0)
    }
    
    // Uses default ship type and empty wallet
    convenience init(name: String, pilotSkill: Int, engineSkill: Int, tradeSkill: Int, fightSkill: Int, difficulty: Difficulty, seed: UInt64) {
        self.init(name: name, pilotSkill: pilotSkill, engineSkill: engineSkill, tradeSkill: tradeSkill, fightSkill: fightSkill, ship: Ship(type: .Gnat), difficulty: difficulty, seed: seed, wallet: 0)
    }
    
    init(name: String, pilotSkill: Int, engineSkill: Int, tradeSkill: Int, fightSkill: Int, ship: Ship, difficulty: Difficulty, seed: UInt64, wallet: Int) {
        self.name = name
        self.pilotSkill = pilotSkill
        self.engineSkill = engineSkill
        self.tradeSkill = tradeSkill
        self.fightSkill = fightSkill
        self.ship = ship
        self.difficulty = difficulty
        self.seed = seed
        self.wallet = wallet
    }
    
    func downloadData(with uid: String, success: @escaping (_ universe: Universe) -> Void, newPlayer: @escaping () -> Void) {
        let ref = FIRDatabase.database().reference(withPath: "Users").child(uid)
        ref.observeSingleEvent(of: .value) { (snapshot) in
            let pSkill = snapshot.childSnapshot(forPath: "pilotSkill").value as? Int
            let eSkill = snapshot.childSnapshot(forPath: "engSkill").value as? Int
            let tSkill = snapshot.childSnapshot(forPath: "tradeSkill").value as? Int
            let fSkill = snapshot.childSnapshot(forPath: "fightSkill").value as? Int
            guard let pilotSkill = pSkill, let engSkill = eSkill, let tradeSkill = tSkill, let fightSkill = fSkill else {
                newPlayer()
                return
            }
            if pilotSkill + engSkill + tradeSkill + fightSkill == 0 {
                newPlayer()
            } else {
                self.pilotSkill = pilotSkill
                self.engineSkill = engSkill
                self.tradeSkill = tradeSkill
                self.fightSkill = fightSkill
                self.name = snapshot.childSnapshot(forPath: "name").value as! String
                self.seed = UInt64(abs(snapshot.childSnapshot(forPath: "seed").value as! Int64))
                self.wallet = snapshot.childSnapshot(forPath: "wallet").value as! Int
                let shipFuelCapacity = snapshot.childSnapshot(forPath: "ship/fuelCapacity").value as! Int
                print(shipFuelCapacity)
                self.ship = Ship(type: ShipType(rawValue: shipFuelCapacity)!)
                self.ship.fuelLevel = snapshot.childSnapshot(forPath: "ship/fuel").value as! Int
                for subSnap in snapshot.childSnapshot(forPath: "ship/cargoHold").children {
                    if let resourceData = subSnap as? [String : Any] {
                        let res = Resource(stringLiteral: resourceData["name"] as! String)
                        let quantity = resourceData["quantity"] as! Int
                        self.ship.cargo[res] = quantity
                    }
                }
                let planetName = snapshot.childSnapshot(forPath: "location/name").value as! String
                var generator = SeededGenerator(seed: self.seed)
                UniverseGenerator.generate(using: &generator, success: { (universe) in
                    let system = universe.solarSystems.first(where: { (system) -> Bool in
                        return system.planets.contains(where: { $0.name == planetName })
                    })
                    let planet = system?.planets.first(where: { $0.name == planetName })
                    self.location = planet
                    success(universe)
                }, fail: { (error) in
                    newPlayer()
                })
            }
        }
    }
}

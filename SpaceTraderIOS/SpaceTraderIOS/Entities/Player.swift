//
//  Player.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/20/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Player: CustomStringConvertible {
    var name: String
    var pilotSkill: Int
    var engineSkill: Int
    var tradeSkill: Int
    var fightSkill: Int
    var ship: Ship
    var difficulty: Difficulty
    var seed: UInt64
    
    var description: String {return "Name: \(name)\nPilot Skill: \(pilotSkill)\nEngine Skill: \(engineSkill)\nTrade Skill: \(tradeSkill)\nFight Skill: \(fightSkill)\nShip Type: \(ship)\nDifficulty: \(difficulty)"}
    
    convenience init() {
        self.init(name: "", pilotSkill: 0, engineSkill: 0, tradeSkill: 0, fightSkill: 0, difficulty: .Easy, seed: 0)
    }
    
    // Uses default ship type
    convenience init(name: String, pilotSkill: Int, engineSkill: Int, tradeSkill: Int, fightSkill: Int, difficulty: Difficulty, seed: UInt64) {
        self.init(name: name, pilotSkill: pilotSkill, engineSkill: engineSkill, tradeSkill: tradeSkill, fightSkill: fightSkill, ship: Ship(type: .Gnat), difficulty: difficulty, seed: seed)
    }
    
    init(name: String, pilotSkill: Int, engineSkill: Int, tradeSkill: Int, fightSkill: Int, ship: Ship, difficulty: Difficulty, seed: UInt64) {
        self.name = name
        self.pilotSkill = pilotSkill
        self.engineSkill = engineSkill
        self.tradeSkill = tradeSkill
        self.fightSkill = fightSkill
        self.ship = ship
        self.difficulty = difficulty
        self.seed = seed
        
    }
}

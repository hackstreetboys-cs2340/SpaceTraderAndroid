//
//  Ship.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/3/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Ship {
    let type: ShipType
    let capacity: Int
    var cargo: [Resource : Int]
    init(type: ShipType, cargo: [Resource : Int]) {
        self.type = type
        self.capacity = type.rawValue
        self.cargo = cargo
    }
    convenience init(type: ShipType) {
        self.init(type: type, cargo: [:])
    }
    convenience init() {
        self.init(type: .Gnat)
    }
    func loadCargo(_ resource: Resource, amount: Int) {
        if cargo.contains(where: { $0.key == resource }) {
            cargo[resource]! += amount
        } else {
            cargo[resource] = amount
        }
    }
    func removeCargo(_ resource: Resource, amount: Int) {
        if cargo.contains(where: { $0.key == resource }) {
            cargo[resource]! -= amount
        }
    }
}

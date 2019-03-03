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
    let cargo: [Resource : Int]
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
}

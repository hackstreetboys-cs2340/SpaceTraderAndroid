//
//  MarketGood.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/3/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class MarketGood {
    let good: Resource
    var price: Int
    var quantity: Int
    
    init(good: Resource, price: Int, quantity: Int) {
        self.good = good
        self.price = price
        self.quantity = quantity
    }
}

extension MarketGood: Equatable {
    static func == (lhs: MarketGood, rhs: MarketGood) -> Bool {
        return lhs.good == rhs.good
    }
}

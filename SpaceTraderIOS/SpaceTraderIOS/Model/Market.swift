//
//  Market.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/1/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Market {
    let planet: Planet
    let player: Player
    var goodsForSale: [MarketGood]
    var goodsToSell: [MarketGood]
    init(planet: Planet, player: Player) {
        self.planet = planet
        self.player = player
        goodsForSale = []
        goodsToSell = []
        generateGoodsForSale()
    }
    
    func generateGoodsForSale() {
        for resource in Resources.allCases {
            if resource.rawValue.canBuy(from: planet) {
                let price = resource.rawValue.price(on: planet)
                let good = MarketGood(good: resource.rawValue, price: price, quantity: <#T##Int#>)
                goodsForSale.append(good)
            }
        }
    }
    func generateGoodsToSell() {
        for resource in Resources.allCases {
            if resource.rawValue.canSell(to: planet) {
                goodsForSale[resource.rawValue] = resource.rawValue.price(on: planet)
            }
        }
    }
}

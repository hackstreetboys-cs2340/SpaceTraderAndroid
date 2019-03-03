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
                let quantity = Int.random(in: 0...100)
                let good = MarketGood(good: resource.rawValue, price: price, quantity: quantity)
                goodsForSale.append(good)
            }
        }
    }
    func generateGoodsToSell() {
        for resource in Resources.allCases {
            if resource.rawValue.canSell(to: planet) {
                let price = resource.rawValue.price(on: planet)
                if let quantity = player.ship.cargo[resource.rawValue] {
                    let good = MarketGood(good: resource.rawValue, price: price, quantity: quantity)
                    goodsForSale.append(good)
                } else {
                    let good = MarketGood(good: resource.rawValue, price: price, quantity: 0)
                    goodsForSale.append(good)
                }
                
            }
        }
    }
}

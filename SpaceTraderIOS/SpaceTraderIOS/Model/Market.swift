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
    var goodsForSale: [Resource : Int]
    var goodsToSell: [Resource : Int]
    init(planet: Planet) {
        self.planet = planet
        goodsForSale = [:]
        goodsToSell = [:]
        generateGoodsForSale()
        generateGoodsToSell()
    }
    
    func generateGoodsForSale() {
        for resource in Resources.allCases {
            if resource.rawValue.canBuy(from: planet) {
                goodsForSale[resource.rawValue] = resource.rawValue.price(on: planet)
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

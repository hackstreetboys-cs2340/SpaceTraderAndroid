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
    
    func buy(good: MarketGood, fail: @escaping (MarketFailure) -> Void) {
        if isAvailable(good) {
            if canAfford(good) {
                if hasSpace() {
                    let marketGood = goodsForSale.first(where: {$0 == good})!
                    marketGood.quantity -= 1
                    player.wallet -= marketGood.price
                    player.ship.cargo[good.good]! += 1
                } else {
                    fail(.cargoFull)
                }
            } else {
                fail(.cannotAfford)
            }
        } else {
            fail(.soldOut)
        }
    }
    func sell(good: MarketGood, fail: @escaping (MarketFailure) -> Void) {
        if hasItem(good) {
            player.ship.cargo[good.good]! -= 1
            player.wallet += goodsToSell.first(where: { $0 == good})!.price
            if let good = goodsForSale.first(where: { $0 == good}) {
             good.quantity += 1
            } else {
                goodsForSale.append(MarketGood(good: good.good, price: good.price, quantity: 1))
            }
        } else {
            fail(.outOfItem)
        }
    }
    
    private func isAvailable(_ good: MarketGood) -> Bool {
        if let good = goodsForSale.first(where: {$0 == good}) {
            return good.quantity > 0
        }
        return false
    }
    private func canAfford(_ good: MarketGood) -> Bool {
        return good.price < player.wallet
    }
    private func hasSpace() -> Bool {
        return player.ship.cargo.count < player.ship.capacity
    }
    private func hasItem(_ good: MarketGood) -> Bool {
        if let quantity = player.ship.cargo[good.good] {
            return quantity > 0
        }
        return false
    }
}

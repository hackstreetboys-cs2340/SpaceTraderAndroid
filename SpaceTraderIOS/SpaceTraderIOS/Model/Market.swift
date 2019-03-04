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
        generateGoodsToSell()
    }
    
    func generateGoodsForSale() {
        print("Generating buy goods...")
        for resource in Resources.allCases {
            if resource.rawValue.canBuy(from: planet) {
                let price = resource.rawValue.price(on: planet)
                let quantity = Int.random(in: 0...100)
                let good = MarketGood(good: resource.rawValue, price: price, quantity: quantity)
                goodsForSale.append(good)
            } else {
                print("Cannot buy \(resource.rawValue.name) from \(planet.name)")
            }
        }
    }
    func generateGoodsToSell() {
        print("Generating sell goods...")
        for resource in Resources.allCases {
            if resource.rawValue.canSell(to: planet) {
                print("Can sell \(resource.rawValue.name) to \(planet.name)")
                let price = resource.rawValue.price(on: planet)
                if let quantity = player.ship.cargo[resource.rawValue] {
                    print("Resource \"\(resource.rawValue.name)\" is in the cargo")
                    let good = MarketGood(good: resource.rawValue, price: price, quantity: quantity)
                    goodsToSell.append(good)
                } else {
                    print("Resource \"\(resource.rawValue.name)\" is not in the cargo")
                    let good = MarketGood(good: resource.rawValue, price: price, quantity: 0)
                    goodsToSell.append(good)
                }
                
            } else {
                print("Cannot sell \(resource.rawValue.name) to \(planet.name)")
            }
        }
    }
    
    func buy(good: MarketGood, amount: Int, fail: @escaping (MarketFailure) -> Void) {
        if isAvailable(good, amount: amount) {
            if canAfford(good, amount: amount) {
                if hasSpace(amount: amount) {
                    let marketGood = goodsForSale.first(where: {$0.good == good.good})!
                    marketGood.quantity -= amount
                    player.wallet -= marketGood.price * amount
                    player.ship.loadCargo(good.good, amount: amount)
                } else {
                    fail(.cargoFull)
                }
            } else {
                fail(.cannotAfford)
            }
        } else {
            fail(.unavailable)
        }
    }
    func sell(good: MarketGood, amount: Int, fail: @escaping (MarketFailure) -> Void) {
        if hasItem(good, amount: amount) {
            player.ship.removeCargo(good.good, amount: amount)
            player.wallet += goodsToSell.first(where: { $0 == good})!.price * amount
            if let good = goodsForSale.first(where: { $0.good == good.good}) {
                good.quantity += amount
                print("New quantity: \(good.quantity).")
            } else {
                goodsForSale.append(MarketGood(good: good.good, price: good.price, quantity: amount))
                print("Good wasn't in market before.")
            }
        } else {
            fail(.outOfItem)
        }
    }
    
    private func isAvailable(_ good: MarketGood, amount: Int) -> Bool {
        if let good = goodsForSale.first(where: {$0.good == good.good}) {
            return good.quantity >= amount
        }
        return false
    }
    private func canAfford(_ good: MarketGood, amount: Int) -> Bool {
        return good.price * amount < player.wallet
    }
    private func hasSpace(amount: Int) -> Bool {
        return player.ship.cargo.count + amount <= player.ship.capacity
    }
    private func hasItem(_ good: MarketGood, amount: Int) -> Bool {
        if let quantity = player.ship.cargo[good.good] {
            return quantity >= amount
        }
        return false
    }
}

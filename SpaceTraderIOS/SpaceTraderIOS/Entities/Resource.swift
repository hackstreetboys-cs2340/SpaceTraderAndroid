//
//  Resource.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/2/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Resource: Equatable, ExpressibleByStringLiteral {
    let name: String
    let MTLP: Int
    let MTLU: Int
    let TTP: Int
    let basePrice: Int
    let IPL: Int
    let variance: Int
    let IE: Event?
    let CR: Condition?
    let ER: Condition?
    let MTL: Int
    let MTH: Int
    
    
    init(name: String, MTLP: Int, MTLU: Int, TTP: Int, basePrice: Int, IPL: Int, variance: Int, IE: Event, CR: Condition, ER: Condition, MTL: Int, MTH: Int) {
        self.name = name
        self.MTLP = MTLP
        self.MTLU = MTLU
        self.TTP = TTP
        self.basePrice = basePrice
        self.IPL = IPL
        self.variance = variance
        self.IE = IE
        self.CR = CR
        self.ER = ER
        self.MTL = MTL
        self.MTH = MTH
    }
    
    public func price(on planet: Planet) -> Double {
        let price = basePrice + (IPL * (planet.techLevel.rawValue - MTLP))
        let direction = Int.random(in: 0...1) == 1 ? -1 : 1
        let computedVariance = 0.01 * Double(Int.random(in: 0...variance) * direction)
        let computedPrice = Double(price) + computedVariance
        return computedPrice
    }
    
    public func canSell(on planet: Planet) -> Bool {
        return planet.techLevel.rawValue > MTLP
    }
    public func canBuy(on planet: Planet) -> Bool {
        return planet.techLevel.rawValue > MTLU
    }
    
    public static func == (lhs: Resource, rhs: Resource) -> Bool {
        return (lhs.name == rhs.name &&
            lhs.MTLP == rhs.MTLP &&
            lhs.MTLU == rhs.MTLU &&
            lhs.TTP == rhs.TTP &&
            lhs.basePrice == rhs.basePrice &&
            lhs.IPL == rhs.IPL &&
            lhs.variance == rhs.variance &&
            lhs.IE == rhs.IE &&
            lhs.CR == rhs.CR &&
            lhs.ER == rhs.ER &&
            lhs.MTL == rhs.MTL &&
            lhs.MTH == rhs.MTH
        )
    }
    
    public required init(stringLiteral value: String) {
        let components = value.components(separatedBy: ",")
        if components.count == 12 {
            self.name = components[0]
            if let mtlp = Int(components[1]) { self.MTLP = mtlp } else { self.MTLP = 0 }
            if let mtlu = Int(components[2]) { self.MTLU = mtlu } else { self.MTLU = 0 }
            if let ttp = Int(components[3]) { self.TTP = ttp } else { self.TTP = 0 }
            if let basePrice = Int(components[4]) { self.basePrice = basePrice } else { self.basePrice = 0 }
            if let ipl = Int(components[5]) { self.IPL = ipl } else { self.IPL = 0 }
            if let variance = Int(components[5]) { self.variance = variance } else {  self.variance = 0 }
            if let ie = Event(rawValue: components[6]) { self.IE = ie } else { self.IE = nil }
            if let cr = Condition(rawValue: components[7]) { self.CR = cr } else { self.CR = nil }
            if let er = Condition(rawValue: components[8]) { self.ER = er } else { self.ER = nil }
            if let mtl = Int(components[9]) { self.MTL = mtl } else { self.MTL = 0 }
            if let mth = Int(components[10]) { self.MTH = mth } else { self.MTH = 0}
        } else {
            print("The enum's string value was not determined properly (has too many or too few words)")
            self.name = ""
            self.MTLP = 0
            self.MTLU = 0
            self.TTP = 0
            self.basePrice = 0
            self.IPL = 0
            self.variance = 0
            self.IE = nil
            self.CR = nil
            self.ER = nil
            self.MTL = 0
            self.MTH = 0
        }
    }
    public required convenience init(unicodeScalarLiteral value: String) {
        self.init(stringLiteral: value)
    }
    public required convenience init(extendedGraphemeClusterLiteral value: String) {
        self.init(stringLiteral: value)
    }
}

extension Resource: Hashable {
    func hash(into hasher: inout Hasher) {
        hasher.combine(name)
    }
}

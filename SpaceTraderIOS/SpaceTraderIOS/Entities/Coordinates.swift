//
//  Coordinates.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/21/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

class Coordinates: CustomStringConvertible {
    var latitude: Double
    var longitude: Double
    
    var description: String {
        return "Latitude: \(latitude)\tLongitude: \(longitude)"
    }
    
    convenience init() {
        self.init(latitude: 0, longitude: 0)
    }
    convenience init(latitude: Int, longitude: Int) {
        self.init(latitude: Double(latitude), longitude: Double(longitude))
    }
    init(latitude:Double, longitude: Double) {
        self.latitude = latitude
        self.longitude = longitude
    }
}

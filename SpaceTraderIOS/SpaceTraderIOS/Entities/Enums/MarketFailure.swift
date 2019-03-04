//
//  MarketFailure.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/3/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

enum MarketFailure: String {
    case unavailable = "The market does not have enough of this item."
    case cannotAfford = "You cannot afford this item."
    case outOfItem = "You do not have any more of this item to sell."
    case cargoFull = "Your ship's cargo hold is full"
}

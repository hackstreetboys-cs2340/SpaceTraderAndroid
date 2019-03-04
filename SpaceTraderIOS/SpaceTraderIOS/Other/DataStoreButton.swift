//
//  DataStoreButton.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/4/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit

class DataStoreButton: UIButton {
    var objects: [String : Any]
    override init(frame: CGRect) {
        self.objects = [:]
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.objects = [:]
        super.init(coder: aDecoder)
    }
}

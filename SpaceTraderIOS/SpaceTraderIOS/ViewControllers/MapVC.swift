//
//  MapVC.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 4/5/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit
import SpriteKit

class MapVC: UIViewController {
    var solarSystems: [SolarSystem] = []
    override func viewDidLoad() {
        if let skView = view as? SKView {
            if let scene = SKScene(fileNamed: "MapScene") as? MapScene {
                scene.solarSystems = solarSystems
                skView.presentScene(scene)
            } else {
                print("Scene doesn't exist")
            }
            skView.ignoresSiblingOrder = true
        } else {
            print("view is not an skview")
        }
    }
}


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
    var player: Player?
    var universe: Universe?
    
    var solarSystems: [SolarSystem] = []
    var mapScene: MapScene?
    var travelBtn: UIButton = UIButton()
    var destination: SolarSystem?
    override func viewDidLoad() {
        if let skView = view as? SKView {
            if let scene = SKScene(fileNamed: "MapScene") as? MapScene {
                mapScene = scene
                scene.solarSystems = solarSystems
                skView.presentScene(scene)
                setupButton()
            } else {
                print("Scene doesn't exist")
            }
            skView.ignoresSiblingOrder = true
        } else {
            print("view is not an skview")
        }
    }
    
    private func setupButton() {
        let offset: CGFloat = 20
        let size: CGSize = CGSize(width: 80, height: 40)
        let pos: CGPoint = CGPoint(x: view.frame.width - size.width - offset, y: view.frame.height - size.height - offset)
        travelBtn.frame = CGRect(origin: pos, size: size)
        travelBtn.backgroundColor = Colors.primaryColor
        travelBtn.setTitleColor(Colors.textColor, for: .normal)
        travelBtn.setTitle("Travel", for: .normal)
        travelBtn.addTarget(self, action: #selector(setDestination(_:)), for: .touchDown)
        travelBtn.addTarget(self, action: #selector(travel(_:)), for: .touchUpInside)
        travelBtn.layer.cornerRadius = 8
        view.addSubview(travelBtn)
    }
    
    @objc private func setDestination(_ sender: Any) {
        if let scene = mapScene {
            if let destName = scene.selectedNode?.name {
                destination = solarSystems.first(where: {$0.name == destName})
            }
        }
    }
    
    @objc private func travel(_ sender: Any) {
        if let _ = destination {
            performSegue(withIdentifier: "MapToSystem", sender: nil)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let dest = segue.destination as? SolarSystemVC, let system = destination, let player = self.player, let universe = self.universe {
            dest.solarSystem = system
            dest.player = player
            dest.universe = universe
        }
    }
    
}


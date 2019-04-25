//
//  SolarSystemVC.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 4/25/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit
import SpriteKit

class SolarSystemVC: UIViewController {
    var solarSystem: SolarSystem?
    var player: Player?
    var universe: Universe?
    
    var travelBtn: UIButton = UIButton()
    var systemLbl: UILabel = UILabel()
    var solarSystemScene: SolarSystemScene?
    var destination: Planet?
    
    let offset = 40
    let titleHeight = 60
    override func viewDidLoad() {
        if let skView = view as? SKView {
            if let scene = SKScene(fileNamed: "SolarSystemScene") as? SolarSystemScene{
                solarSystemScene = scene
                if let planets = solarSystem?.planets {
                    scene.planets = planets
                }
                scene.titleOffset = titleHeight + offset
                skView.presentScene(scene)
                setupLabel()
                setupButton()
            } else {
                print("Scene doesn't exist")
            }
            skView.ignoresSiblingOrder = true
        } else {
            print("view is not an skview")
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let dest = segue.destination as? PlanetVC {
            if let planet = destination, let player = self.player, let universe = self.universe {
                dest.planet = planet
                dest.player = player
                dest.universe = universe
            }
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
    
    private func setupLabel() {
        let offset: CGFloat = CGFloat(self.offset)
        let size: CGSize = CGSize(width: view.frame.width, height: CGFloat(titleHeight))
        let pos: CGPoint = CGPoint(x: 0, y: offset)
        systemLbl.frame = CGRect(origin: pos, size: size)
        systemLbl.text = solarSystem?.name ?? ""
        systemLbl.textColor = Colors.textColor
        systemLbl.textAlignment = .center
        systemLbl.font = UIFont(name: systemLbl.font.fontName, size: 50)
        view.addSubview(systemLbl)
    }
    
    @objc private func setDestination(_ sender: Any) {
        if let scene = solarSystemScene {
            if let destName = scene.selectedNode?.name {
                let trimmedDestName = destName.replacingOccurrences(of: "Planet: ", with: "")
                destination = solarSystem?.planets.first(where: {$0.name == trimmedDestName})
            }
        }
    }
    
    @objc private func travel(_ sender: Any) {
        if let _ = destination {
            performSegue(withIdentifier: "SystemToPlanet", sender: nil)
        }
    }
}

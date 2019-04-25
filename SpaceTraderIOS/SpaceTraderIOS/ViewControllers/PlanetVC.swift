//
//  PlanetVC.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/1/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit

class PlanetVC: UIViewController {
    var player: Player?
    var planet: Planet?
    var universe: Universe?
    
    let planetNameLbl = UILabel()
    let mapButton = UIButton()
    let marketButton = UIButton()
    
    override func viewDidLoad() {
        player?.location = planet
        layoutUI()
    }
    private func layoutUI() {
        view.backgroundColor = Colors.backgroundColor
        
        let offset: CGFloat = 20
        var width: CGFloat = 160
        var height: CGFloat = 40
        var size = CGSize(width: width, height: height)
        var pos = CGPoint(x: offset, y: view.frame.height - offset * 4)
        marketButton.frame = CGRect(origin: pos, size: size)
        marketButton.setTitle("Market", for: .normal)
        marketButton.backgroundColor = Colors.primaryColor
        marketButton.setTitleColor(Colors.textColor, for: .normal)
        marketButton.layer.cornerRadius = 5
        marketButton.addTarget(self, action: #selector(marketTapped(_:)), for: .touchUpInside)
        
        pos = CGPoint(x: offset, y: marketButton.frame.minY - offset - size.height)
        mapButton.frame = CGRect(origin: pos, size: size)
        mapButton.setTitle("Map", for: .normal)
        mapButton.backgroundColor = Colors.primaryColor
        mapButton.setTitleColor(Colors.textColor, for: .normal)
        mapButton.layer.cornerRadius = 5
        mapButton.addTarget(self, action: #selector(mapTapped(_:)), for: .touchUpInside)
        
        width = view.frame.width
        height = 70
        size = CGSize(width: width, height: height)
        pos = CGPoint(x: 0, y: offset * 4)
        planetNameLbl.frame = CGRect(origin: pos, size: size)
        planetNameLbl.textAlignment = .center
        planetNameLbl.text = planet?.name
        planetNameLbl.textColor = Colors.textColor
        planetNameLbl.font = UIFont(name: planetNameLbl.font.fontName, size: height - 10)
        
        view.addSubview(marketButton)
        view.addSubview(mapButton)
        view.addSubview(planetNameLbl)
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let dest = segue.destination as? MarketVC {
            dest.player = player
            guard let planet = planet else {
                print("Planet is nil.")
                return
            }
            guard let player = player else {
                print("Player is nil.")
                return
            }
            setupTestData()
            print(player.ship.cargo)
            let market = Market(planet: planet, player: player)
            dest.market = market
            print("GOODS:\n\(market.goodsToSell)")
        }
        
        if let dest = segue.destination as? MapVC {
            guard let systems = universe?.solarSystems else { return }
            guard let player = self.player else { return }
            guard let universe = self.universe else { return }
            dest.solarSystems = systems
            dest.player = player
            dest.universe = universe
        }
    }
    @objc private func marketTapped(_ sender: Any) {
        performSegue(withIdentifier: "PlanetToMarket", sender: sender)
    }
    
    @objc private func mapTapped(_ sender: Any) {
        performSegue(withIdentifier: "PlanetToMap", sender: sender)
    }
    
    private func setupTestData() {
        if let player = player {
            player.ship.loadCargo(Resources.Firearms.rawValue, amount: 5)
            player.ship.loadCargo(Resources.Food.rawValue, amount: 88)
            player.ship.loadCargo(Resources.Water.rawValue, amount: 102)
            player.ship.loadCargo(Resources.Games.rawValue, amount: 12)
            player.ship.loadCargo(Resources.Furs.rawValue, amount: 42)
            player.ship.loadCargo(Resources.Robots.rawValue, amount: 1)
        }
    }
}

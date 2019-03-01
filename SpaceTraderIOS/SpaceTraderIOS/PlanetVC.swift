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
    var planet: Planet?
    var universe: Universe?
    
    let planetNameLbl = UILabel()
    let marketButton = UIButton()
    
    override func viewDidLoad() {
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
        
        width = view.frame.width
        height = 60
        size = CGSize(width: width, height: height)
        pos = CGPoint(x: 0, y: offset * 4)
        planetNameLbl.frame = CGRect(origin: pos, size: size)
        planetNameLbl.textAlignment = .center
        planetNameLbl.text = planet?.name
        planetNameLbl.textColor = Colors.textColor
        planetNameLbl.font = UIFont(name: planetNameLbl.font.fontName, size: height)
        
        view.addSubview(marketButton)
        view.addSubview(planetNameLbl)
    }
}

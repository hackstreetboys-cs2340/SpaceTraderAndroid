//
//  ViewController.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/19/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    let startButton = UIButton()
    let titleLbl = UILabel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup afterloading the view, typically from a nib.
        layoutUI()
    }
    
    private func layoutUI() {
        view.backgroundColor = Colors.backgroundColor
        
        let offset: CGFloat = 10
        let height: CGFloat = 40
        var size = CGSize(width: view.frame.width - (2 * offset), height: height)
        var pos = CGPoint(x: offset, y: view.frame.height * 0.75)
        startButton.frame = CGRect(origin: pos, size: size)
        startButton.backgroundColor = Colors.primaryColor
        startButton.setTitleColor(Colors.textColor, for: .normal)
        startButton.layer.cornerRadius = 5
        startButton.setTitle("Start", for: .normal)
        startButton.addTarget(self, action: #selector(startTapped(_:)), for: .touchUpInside)
        
        pos = CGPoint(x: offset, y: offset * 4)
        size = CGSize(width: view.frame.width - 2 * offset, height: 80)
        titleLbl.frame = CGRect(origin: pos, size: size)
        titleLbl.text = "Space Trader"
        titleLbl.textColor = Colors.textColor
        titleLbl.font = UIFont(name: titleLbl.font.fontName, size: 40)
        titleLbl.textAlignment = .center
        
        view.addSubview(startButton)
        view.addSubview(titleLbl)
    }

    @objc private func startTapped(_ sender: Any) {
        print("Tapped")
        performSegue(withIdentifier: "mainToConfig", sender: sender)
    }

}


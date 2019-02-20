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
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup afterloading the view, typically from a nib.
        layoutUI()
    }
    
    private func layoutUI() {
        view.backgroundColor = Colors.backgroundColor
        
        let offset: CGFloat = 10
        let height: CGFloat = 40
        let size = CGSize(width: view.frame.width - (2 * offset), height: height)
        let pos = CGPoint(x: offset, y: view.frame.height * 0.75)
        startButton.frame = CGRect(origin: pos, size: size)
        startButton.backgroundColor = Colors.primaryColor
        startButton.setTitleColor(Colors.textColor, for: .normal)
        startButton.layer.cornerRadius = 5
        startButton.setTitle("Start", for: .normal)
        startButton.addTarget(self, action: #selector(startTapped(_:)), for: .touchUpInside)
        
        view.addSubview(startButton)
    }

    @objc private func startTapped(_ sender: Any) {
        performSegue(withIdentifier: "mainToConfig", sender: sender)
    }

}


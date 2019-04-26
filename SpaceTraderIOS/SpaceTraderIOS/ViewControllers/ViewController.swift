//
//  ViewController.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/19/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import UIKit
import GoogleSignIn
import FirebaseAuth

class ViewController: UIViewController, GIDSignInUIDelegate {

    let startButton = UIButton()
    let titleLbl = UILabel()
    let googleBtn = GIDSignInButton()
    
    var universe: Universe?
    var player: Player?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup afterloading the view, typically from a nib.
        layoutUI()
        GIDSignIn.sharedInstance()?.uiDelegate = self
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let dest = segue.destination as? PlanetVC, let universe = universe, let player = player {
            dest.universe = universe
            dest.player = player
            dest.planet = player.location
        }
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
        
        /*
        titleLbl.frame = CGRect(origin: pos, size: size)
        titleLbl.text = "Space Trader"
        titleLbl.textColor = Colors.textColor
        titleLbl.font = UIFont(name: titleLbl.font.fontName, size: 40)
        titleLbl.textAlignment = .center
        */
        
        googleBtn.frame = CGRect(origin: pos, size: size)
        
        view.addSubview(startButton)
        view.addSubview(googleBtn)
        //view.addSubview(titleLbl)
    }

    @objc private func startTapped(_ sender: Any) {
        print("Tapped")
        performSegue(withIdentifier: "mainToConfig", sender: sender)
    }
    
    func sign(inWillDispatch signIn: GIDSignIn!, error: Error!) {
        if let error = error {
            return
        }
        let googleUser = signIn.currentUser
        guard let auth = googleUser?.authentication else { return }
        let credential = FIRGoogleAuthProvider.credential(withIDToken: auth.idToken,
                                                       accessToken: auth.accessToken)
        FIRAuth.auth()?.signIn(with: credential, completion: { (user, error) in
            if let error = error {
                print("There was an error retrieving the user's data from Firebase.\nError: \(error)")
                return
            }
            if let user = user {
                let player = Player()
                player.downloadData(with: user.uid, success: { (universe) in
                    self.universe = universe
                    self.player = player
                    DispatchQueue.main.async {
                        self.performSegue(withIdentifier: "LoginToPlanet", sender: nil)
                    }
                }, newPlayer: {
                    // do stuff if a new player signed on
                })
            }
        })
    }
    
    private func onReturningUser() {
        
    }

}

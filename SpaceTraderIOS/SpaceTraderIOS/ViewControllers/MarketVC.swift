//
//  MarketVC.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/1/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit

class MarketVC: UIViewController {
    let market: Market? = nil
    
    let buyItemsView = UITableView()
    let sellItemsView = UITableView()
    override func viewDidLoad() {
        layoutUI()
    }
    private func layoutUI() {
        
    }
}

extension MarketVC: UITableViewDelegate {
    
}
extension MarketVC: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let market = market {
            if tableView == self.buyItemsView {
                return market.goodsForSale.count
            } else if tableView == self.sellItemsView {
                return market.goodsToSell.count
            }
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        <#code#>
    }
    
    
}

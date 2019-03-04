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
    var market: Market? = nil
    var player: Player? = nil
    
    let buyItemsTableView = UITableView()
    let sellItemsTableView = UITableView()
    let buyLbl = UILabel()
    let sellLbl = UILabel()
    let walletLbl = UILabel()
    let separator = UIView()
    override func viewDidLoad() {
        print("CARGO:")
        print(player?.ship.cargo)
        print(market?.goodsForSale)
        print(market?.goodsToSell)
        layoutUI()
    }
    
    private func layoutUI() {
        view.backgroundColor = Colors.backgroundColor
        buyItemsTableView.dataSource = self
        buyItemsTableView.delegate = self
        buyItemsTableView.separatorColor = Colors.secondaryColor
        buyItemsTableView.register(BuyItemCell.self, forCellReuseIdentifier: "Buy Item Cell")
        sellItemsTableView.dataSource = self
        sellItemsTableView.delegate = self
        sellItemsTableView.separatorColor = Colors.secondaryColor
        sellItemsTableView.register(SellItemCell.self, forCellReuseIdentifier: "Sell Item Cell")
        
        let offset: CGFloat = 20
        var width: CGFloat = view.frame.width / 2
        var height: CGFloat = 30
        var yPos: CGFloat = offset * 2
        var xPos: CGFloat = view.frame.width - offset - width
        var pos = CGPoint(x: xPos, y: yPos)
        var size = CGSize(width: width, height: height)
        let separatorHeight: CGFloat = 2
        walletLbl.frame = CGRect(origin: pos, size: size)
        walletLbl.textAlignment = .right
        walletLbl.textColor = Colors.textColor
        walletLbl.font = UIFont(name: buyLbl.font.fontName, size: 30)
        if let wallet = player?.wallet {
            walletLbl.text = "Wallet: \(wallet)"
        } else {
            walletLbl.text = "Wallet: 0"
            print("Player is nil.")
        }
        xPos = offset
        pos = CGPoint(x: xPos, y: yPos)
        buyLbl.frame = CGRect(origin: pos, size: size)
        buyLbl.text = "Buy"
        buyLbl.font = UIFont(name: buyLbl.font.fontName, size: 30)
        buyLbl.textColor = Colors.textColor
        buyLbl.textAlignment = .left
        yPos = buyLbl.frame.maxY + offset
        width = view.frame.width - offset * 2
        height = view.frame.height / 2 - buyLbl.frame.height * 2 - separatorHeight
        pos = CGPoint(x: xPos, y: yPos)
        size = CGSize(width: width, height: height)
        buyItemsTableView.frame = CGRect(origin: pos, size: size)
        buyItemsTableView.backgroundColor = Colors.backgroundColor
        separator.frame = CGRect(origin: CGPoint(x: 0, y: buyItemsTableView.frame.maxY), size: CGSize(width: view.frame.width, height: separatorHeight))
        separator.backgroundColor = Colors.secondaryColor
        height = 30
        size = CGSize(width: width, height: height)
        yPos = separator.frame.maxY + offset / 2
        pos = CGPoint(x: xPos, y: yPos)
        sellLbl.frame = CGRect(origin: pos, size: size)
        sellLbl.text = "Sell"
        sellLbl.textColor = Colors.textColor
        sellLbl.textAlignment = .left
        sellLbl.font = UIFont(name: buyLbl.font.fontName, size: 30)
        yPos = sellLbl.frame.maxY + offset / 2
        pos = CGPoint(x: xPos, y: yPos)
        height = view.frame.height / 2 - buyLbl.frame.height * 2 - separatorHeight
        size = CGSize(width: width, height: height)
        sellItemsTableView.frame = CGRect(origin: pos, size: size)
        sellItemsTableView.setContentOffset(pos, animated: false)
        sellItemsTableView.backgroundColor = Colors.backgroundColor

        view.addSubview(walletLbl)
        view.addSubview(buyLbl)
        view.addSubview(buyItemsTableView)
        view.addSubview(separator)
        view.addSubview(sellLbl)
        view.addSubview(sellItemsTableView)
    }
}

extension MarketVC: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    }
}
extension MarketVC: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let market = market {
            if tableView == self.buyItemsTableView {
                return market.goodsForSale.count
            } else if tableView == self.sellItemsTableView {
                return market.goodsToSell.count
            }
            return 0
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let market = market {
            if tableView == self.buyItemsTableView {
                let cell = tableView.dequeueReusableCell(withIdentifier: "Buy Item Cell") as! BuyItemCell
                let good = market.goodsForSale[indexPath.row]
                cell.good = good
                cell.goodNameLbl.text = good.good.name
                cell.goodPriceLbl.text = "$\(good.price)"
                cell.goodQuantityLbl.text = "Stock: \(good.quantity)"
                cell.buyBtn.tag = indexPath.row
                cell.buyBtn.addTarget(self, action: #selector(buy(_:)), for: .touchUpInside)
                return cell
            } else if tableView == self.sellItemsTableView {
                let cell = tableView.dequeueReusableCell(withIdentifier: "Sell Item Cell") as! SellItemCell
                let good = market.goodsToSell[indexPath.row]
                cell.good = good
                cell.goodNameLbl.text = good.good.name
                cell.goodPriceLbl.text = "$\(good.price)"
                cell.sellBtn.tag = indexPath.row
                cell.sellBtn.addTarget(self, action: #selector(sell(_:)), for: .touchUpInside)
                return cell
            }
            return UITableViewCell()
        }
        return UITableViewCell()
    }
    
    @objc private func buy(_ btn: UIButton) {
        guard let cell = buyItemsTableView.cellForRow(at: IndexPath(row: btn.tag, section: 0)) as? BuyItemCell else {
            print("Wrong cell")
            return
        }
        guard let market = market else {
            print("Market is nil.")
            return
        }
        guard let good = cell.good else {
            print("The cell did not get a good assigned to it.")
            return
        }
        guard let amount = Int(String((cell.quantityToBuyField.text?.split(separator: " ")[0])!)) else {
            print("There was something wrong with the cell's quantity label formatting.")
            return
        }
        market.buy(good: good, amount: amount) { (fail) in
            print(fail.rawValue)
        }
        updateWalletText()
        sellItemsTableView.reloadData()
        buyItemsTableView.reloadData()
    }
    
    @objc private func sell(_ btn: UIButton) {
        guard let cell = sellItemsTableView.cellForRow(at: IndexPath(row: btn.tag, section: 0)) as? SellItemCell else {
            print("Wrong cell")
            return
        }
        guard let market = market else {
            print("Market is nil.")
            return
        }
        guard let good = cell.good else {
            print("The cell did not get a good assigned to it.")
            return
        }
        guard let amount = Int(String((cell.quantityToSellField.text?.split(separator: " ")[0])!)) else {
            print("There was something wrong with the cell's quantity label formatting.")
            return
        }
        print("Selling \(amount) of \(good.good.name)")
        market.sell(good: good, amount: amount) { (fail) in
            print(fail.rawValue)
        }
        updateWalletText()
        buyItemsTableView.reloadData()
        sellItemsTableView.reloadData()
    }
    
    private func updateWalletText() {
        if let wallet = player?.wallet {
            walletLbl.text = "Wallet: \(wallet)"
        }
    }
}

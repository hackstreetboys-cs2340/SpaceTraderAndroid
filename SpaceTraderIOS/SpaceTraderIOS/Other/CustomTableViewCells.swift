//
//  CustomTableViewCells.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/3/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit

class BuyItemCell: UITableViewCell {
    let goodNameLbl = UILabel()
    let goodPriceLbl = UILabel()
    let goodQuantityLbl = UILabel()
    let quantityToBuyField = UITextField()
    let buyBtn = UIButton()
    var good: MarketGood? = nil
    override func layoutSubviews() {
        backgroundColor = Colors.backgroundColor
        let offset: CGFloat = 20
        var width: CGFloat = 80
        let height: CGFloat = 40
        var size: CGSize = CGSize(width: width, height: height)
        var xPos: CGFloat = offset
        var yPos: CGFloat = contentView.frame.midY - height
        var pos = CGPoint(x: xPos, y: yPos)
        goodNameLbl.frame = CGRect(origin: pos, size: size)
        goodNameLbl.textAlignment = .left
        goodNameLbl.textColor = Colors.textColor
        yPos = contentView.frame.midY
        pos = CGPoint(x: xPos, y: yPos)
        goodPriceLbl.frame = CGRect(origin: pos, size: size)
        goodPriceLbl.textAlignment = .left
        goodPriceLbl.textColor = Colors.textColor
        xPos = goodPriceLbl.frame.maxX + offset
        pos = CGPoint(x: xPos, y: yPos)
        goodQuantityLbl.frame = CGRect(origin: pos, size: size)
        goodQuantityLbl.textAlignment = .left
        goodQuantityLbl.textColor = Colors.textColor

        width = 40
        size = CGSize(width: width, height: height)
        yPos = contentView.frame.midY - height / 2
        xPos = contentView.frame.width - offset - width
        pos = CGPoint(x: xPos, y: yPos)
        buyBtn.frame = CGRect(origin: pos, size: size)
        buyBtn.setTitle("Buy", for: .normal)
        buyBtn.backgroundColor = Colors.primaryColor
        buyBtn.setTitleColor(Colors.textColor, for: .normal)
        buyBtn.layer.cornerRadius = 5

        xPos = buyBtn.frame.minX - width - offset
        pos = CGPoint(x: xPos, y: yPos)
        quantityToBuyField.frame = CGRect(origin: pos, size: size)
        quantityToBuyField.textAlignment = .center
        quantityToBuyField.textColor = Colors.textColor
        quantityToBuyField.text = "0"
        
        contentView.addSubview(goodNameLbl)
        contentView.addSubview(goodPriceLbl)
        contentView.addSubview(goodQuantityLbl)
        contentView.addSubview(buyBtn)
        contentView.addSubview(quantityToBuyField)
    }
}

class SellItemCell: UITableViewCell {
    let goodNameLbl = UILabel()
    let goodPriceLbl = UILabel()
    let quantityToSellField = UITextField()
    let sellBtn = UIButton()
    var good: MarketGood? = nil
    override func layoutSubviews() {
        backgroundColor = Colors.backgroundColor
        let offset: CGFloat = 20
        var width: CGFloat = 80
        let height: CGFloat = 40
        var size: CGSize = CGSize(width: width, height: height)
        var xPos: CGFloat = offset
        var yPos: CGFloat = contentView.frame.midY - height
        var pos = CGPoint(x: xPos, y: yPos)
        goodNameLbl.frame = CGRect(origin: pos, size: size)
        goodNameLbl.textAlignment = .left
        goodNameLbl.textColor = Colors.textColor
        yPos = contentView.frame.midY
        pos = CGPoint(x: xPos, y: yPos)
        goodPriceLbl.frame = CGRect(origin: pos, size: size)
        goodPriceLbl.textAlignment = .left
        goodPriceLbl.textColor = Colors.textColor
        width = 40
        size = CGSize(width: width, height: height)
        yPos = contentView.frame.midY - height / 2
        xPos = contentView.frame.width - offset - width
        pos = CGPoint(x: xPos, y: yPos)
        sellBtn.frame = CGRect(origin: pos, size: size)
        sellBtn.setTitle("Sell", for: .normal)
        sellBtn.backgroundColor = Colors.primaryColor
        sellBtn.setTitleColor(Colors.textColor, for: .normal)
        sellBtn.layer.cornerRadius = 5
        xPos = sellBtn.frame.minX - width - offset
        pos = CGPoint(x: xPos, y: yPos)
        quantityToSellField.frame = CGRect(origin: pos, size: size)
        quantityToSellField.textAlignment = .center
        quantityToSellField.textColor = Colors.textColor
        quantityToSellField.text = "0"
        
        contentView.addSubview(goodNameLbl)
        contentView.addSubview(goodPriceLbl)
        contentView.addSubview(sellBtn)
        contentView.addSubview(quantityToSellField)
    }
}

//
//  PlayerConfigVC.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 2/19/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import UIKit

class PlayerConfigVC: UIViewController {
    let titleLbl = UILabel()
    let nameField = UITextField()
    let pilotLbl = UILabel()
    let engineLbl = UILabel()
    let tradeLbl = UILabel()
    let fightLbl = UILabel()
    let availablePointsLbl = UILabel()
    let difficultyLbl = UILabel()
    let difficultyPicker = UIPickerView()
    let doneBtn = UIButton()
    
    let pilotSkillLbl = UILabel()
    let engineSkillLbl = UILabel()
    let tradeSkillLbl = UILabel()
    let fightSkillLbl = UILabel()
    
    let difficultiesText = ["Easy", "Medium", "Hard"]
    let difficulties = [Difficulty.Easy, Difficulty.Medium, Difficulty.Hard]
    var skillLbls: [UILabel] = []
    var pointsAvailable = 16;
    
    var player = Player()
    override func viewDidLoad() {
        layoutUI()
    }
    
    private func layoutUI() {
        view.backgroundColor = Colors.backgroundColor
        
        let offset: CGFloat = 20
        var size = CGSize(width: view.frame.width - 2 * offset, height: 80)
        var pos = CGPoint(x: offset, y: offset * 2)
        titleLbl.frame = CGRect(origin: pos, size: size)
        titleLbl.text = "Configure Player"
        titleLbl.textColor = Colors.textColor
        titleLbl.font = UIFont(name: titleLbl.font.fontName, size: 40)
        titleLbl.textAlignment = .center
        
        size = CGSize(width: view.frame.width / 2, height: 40)
        pos = CGPoint(x: (view.frame.width - size.width) / 2, y: titleLbl.frame.maxY)
        nameField.frame = CGRect(origin: pos, size: size)
        nameField.attributedPlaceholder = NSAttributedString(string: "Name",
                                                             attributes: [NSAttributedString.Key.foregroundColor: Colors.textColor])
        nameField.textAlignment = .center
        nameField.textColor = Colors.textColor
        let underline = CALayer()
        var width:CGFloat = 2
        underline.borderColor = Colors.white.cgColor
        underline.frame = CGRect(x: 0, y: nameField.frame.size.height - width, width: nameField.frame.size.width, height: width)
        underline.borderWidth = width
        nameField.layer.addSublayer(underline)
        
        let displayLabels = [pilotLbl, engineLbl, tradeLbl, fightLbl]
        skillLbls = [pilotSkillLbl, engineSkillLbl, tradeSkillLbl, fightSkillLbl]
        let skillLblTxts = ["Pilot:", "Engine:", "Trade:", "Fight:"]
        
        width = 80
        var height: CGFloat = 40
        size = CGSize(width: width, height: height)
        pos = CGPoint(x: offset, y: view.frame.height / 3)
        pilotLbl.frame = CGRect(origin: pos, size: size)
        pilotLbl.text = "Pilot:"
        pilotLbl.textColor = Colors.textColor
        pilotLbl.textAlignment = .right
        pilotLbl.font = UIFont(name: pilotLbl.font.fontName, size: 20)
        
        var prevY = pilotLbl.frame.maxY
        for (index, lbl) in displayLabels.enumerated() {
            if index != 0 {
                pos = CGPoint(x: offset, y: prevY + offset)
                lbl.frame = CGRect(origin: pos, size: size)
                lbl.text = skillLblTxts[index]
                lbl.textColor = Colors.textColor
                lbl.textAlignment = .right
                lbl.font = UIFont(name: lbl.font.fontName, size: 20)
                view.addSubview(lbl)
                prevY = lbl.frame.maxY
            }
        }
        
        pos = CGPoint(x: offset, y: prevY + 2 * offset)
        size = CGSize(width: view.frame.width - 2 * offset, height: height)
        availablePointsLbl.frame = CGRect(origin: pos, size: size)
        availablePointsLbl.text = "Available points: \(pointsAvailable)"
        availablePointsLbl.textColor = Colors.textColor
        availablePointsLbl.textAlignment = .center
        availablePointsLbl.font = UIFont(name: availablePointsLbl.font.fontName, size: 35)
        
        pos = CGPoint(x: offset, y: availablePointsLbl.frame.maxY + offset * 2)
        size = CGSize(width: width * 2, height: height)
        difficultyLbl.frame = CGRect(origin: pos, size: size)
        difficultyLbl.text = "Difficulty:"
        difficultyLbl.textAlignment = .right
        difficultyLbl.textColor = Colors.textColor
        difficultyLbl.font = UIFont(name: difficultyLbl.font.fontName, size: 20)
        
        height = 80
        width = 100
        pos = CGPoint(x: difficultyLbl.frame.maxX + offset, y: difficultyLbl.frame.midY - height / 2)
        size = CGSize(width: width, height: height)
        difficultyPicker.frame = CGRect(origin: pos, size: size)
        difficultyPicker.tintColor = Colors.lightGray
        difficultyPicker.delegate = self
        difficultyPicker.dataSource = self
        
        height = 40
        pos = CGPoint(x: offset, y: difficultyPicker.frame.maxY + offset)
        size = CGSize(width: view.frame.width - offset * 2, height: height)
        doneBtn.frame = CGRect(origin: pos, size: size)
        doneBtn.setTitle("Done", for: .normal)
        doneBtn.setTitleColor(Colors.textColor, for: .normal)
        doneBtn.backgroundColor = Colors.primaryColor
        doneBtn.layer.cornerRadius = 5
        doneBtn.addTarget(self, action: #selector(doneTapped(_:)), for: .touchUpInside)
        
        view.addSubview(titleLbl)
        view.addSubview(nameField)
        view.addSubview(pilotLbl)
        view.addSubview(availablePointsLbl)
        view.addSubview(difficultyLbl)
        view.addSubview(difficultyPicker)
        view.addSubview(doneBtn)

        
        layoutSkillLabels(for: displayLabels, skillLbls: skillLbls)
    }
    
    private func layoutSkillLabels(for displayLabels: [UILabel], skillLbls: [UILabel]) {
        let offset: CGFloat = 10
        for (index, label) in displayLabels.enumerated() {
            var pos = CGPoint(x: label.frame.maxX + offset, y: label.frame.minY)
            let size = CGSize(width: 30, height: label.frame.size.height)
            let skillLbl = skillLbls[index]
            
            skillLbl.frame = CGRect(origin: pos, size: size)
            skillLbl.text = "0"
            skillLbl.textColor = Colors.textColor
            skillLbl.font = UIFont(name: skillLbl.font.fontName, size: 20)
            
            pos = CGPoint(x: pos.x + size.width + offset * 2, y: pos.y)
            let minusBtn = UIButton(frame: CGRect(origin: pos, size: size))
            minusBtn.setTitle("-", for: .normal)
            minusBtn.setTitleColor(UIColor.red, for: .normal)
            minusBtn.tag = -(index + 1)
            minusBtn.titleLabel?.font = UIFont(name: minusBtn.titleLabel!.font.fontName, size: 20)
            minusBtn.addTarget(self, action: #selector(skillChangeBtnTapped(_:)), for: .touchUpInside)
            
            pos = CGPoint(x: pos.x + size.width + offset, y: pos.y)
            let plusBtn = UIButton(frame: CGRect(origin: pos, size: size))
            plusBtn.setTitle("+", for: .normal)
            plusBtn.setTitleColor(UIColor.green, for: .normal)
            plusBtn.titleLabel?.font = UIFont(name: plusBtn.titleLabel!.font.fontName, size: 20)
            plusBtn.tag = index + 1
            plusBtn.addTarget(self, action: #selector(skillChangeBtnTapped(_:)), for: .touchUpInside)
            
            
            view.addSubview(skillLbl)
            view.addSubview(minusBtn)
            view.addSubview(plusBtn)
        }
    }
    
    @objc private func skillChangeBtnTapped(_ sender: UIButton) {
        let tag = sender.tag
        
        let lbl = skillLbls[abs(tag) - 1]
        var curr = Int(lbl.text!)!
        
        let inc = tag / abs(tag)
        let futurePointsAvailable = pointsAvailable - inc
        
        if futurePointsAvailable >= 0 && curr + inc >= 0 {
            curr += inc
            lbl.text = "\(curr)"
            pointsAvailable = futurePointsAvailable
            availablePointsLbl.text = "Available points: \(pointsAvailable)"
        }
    }
    
    @objc private func doneTapped(_ sender: UIButton) {
        if pointsAvailable == 0 {
            player = Player(name: nameField.text!, pilotSkill: Int(pilotSkillLbl.text!)!, engineSkill: Int(engineSkillLbl.text!)!, tradeSkill: Int(tradeSkillLbl.text!)!, fightSkill: Int(fightSkillLbl.text!)!, difficulty: difficulties[difficultyPicker.selectedRow(inComponent: 0)])
            print(player)
        } else {
            
        }
    }
}

extension PlayerConfigVC: UIPickerViewDataSource {
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return difficultiesText.count
    }
    
    
}

extension PlayerConfigVC: UIPickerViewDelegate {
    func pickerView(_ pickerView: UIPickerView, attributedTitleForRow row: Int, forComponent component: Int) -> NSAttributedString? {
        return NSAttributedString(string: difficultiesText[row],
                                       attributes: [NSAttributedString.Key.foregroundColor: Colors.textColor])
    }
}

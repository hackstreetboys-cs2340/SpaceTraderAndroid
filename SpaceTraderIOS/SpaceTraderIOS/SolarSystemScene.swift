//
//  SolarSystemScene.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 4/25/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import SpriteKit

class SolarSystemScene: SKScene {
    var selectedNode: SKSpriteNode?
    var ufoSprite: SKSpriteNode = SKSpriteNode(imageNamed: "ufo")
    var titleOffset = 0
    var planets: [Planet] = []
    override func didMove(to view: SKView) {
        print("Planets: \(planets)")
        scene?.backgroundColor = Colors.backgroundColor
        scene?.anchorPoint = CGPoint.zero
        layoutPlanets()
        setupUFO()
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        guard let node = nodes(at: touch.location(in: self)).first as? SKSpriteNode else { return }
        if let nodeName = node.name {
            if nodeName.contains("Planet: ") {
                selectedNode = node
                animateUFO(to: node)
            }
        }
    }
    
    private func setupUFO() {
        ufoSprite.size = CGSize(width: 30, height: 15)
        let pos: CGPoint = CGPoint(x: -(ufoSprite.frame.width) , y: view!.frame.height / 2)
        ufoSprite.position = pos
        ufoSprite.name = "ufo"
        addChild(ufoSprite)
    }
    
    private func animateUFO(to planet: SKSpriteNode) {
        let offset: CGFloat = 20
        let destX = planet.position.x - ufoSprite.size.width - planet.size.width / 2
        let destY = planet.position.y + offset
        let currX = ufoSprite.position.x
        let currY = ufoSprite.position.y
        let dest = CGPoint(x: destX, y: destY)
        let diff = sqrt(Double((destY - currY) * (destY - currY) + (destX - currX) * (destX - currX)))
        let velocity: Double = 10
        let duration = diff / velocity
        let moveAction = SKAction.move(to: dest, duration: 2)
        ufoSprite.run(moveAction)
        print("PENIS !")
    }
    
    private func layoutPlanets() {
        let availableHeight = view!.frame.height - CGFloat(titleOffset)
        let offset: CGFloat = 20
        let size: CGSize = CGSize(width: 40, height: 40)
        for (index, planet) in planets.enumerated() {
            let yPosNoOffset: CGFloat = (availableHeight * CGFloat(index + 1)) / CGFloat(planets.count + 1)
            let yPos: CGFloat = offset + yPosNoOffset
            let xPos: CGFloat = view!.frame.width / 2
            let pos: CGPoint = CGPoint(x: xPos, y: yPos)
            let planetNode: SKSpriteNode = SKSpriteNode()
            planetNode.position = pos
            planetNode.size = size
            formatAndAddNode(planetNode, planet: planet)
        }
    }
    private func formatAndAddNode(_ planetNode: SKSpriteNode, planet: Planet) {
        let imageName = Constants.planetTypes.randomElement()
        planetNode.texture = SKTexture(imageNamed: imageName!)
        planetNode.name = "Planet: \(planet.name)"
        addChild(planetNode)
        setupLabels(for: planetNode, planet: planet)
    }
    private func setupLabels(for planetNode: SKSpriteNode, planet: Planet) {
        let offset: CGFloat = 40
        let labelNode = SKLabelNode(text: planet.name)
        let pos: CGPoint = CGPoint(x: planetNode.position.x + planetNode.size.width + offset, y: planetNode.position.y - planetNode.size.height / 2 + 5)
        labelNode.position = pos
        addChild(labelNode)
    }
}

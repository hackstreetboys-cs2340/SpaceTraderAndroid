//
//  MapScene.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 4/5/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation
import SpriteKit

var mapNode: SKSpriteNode?

class MapScene: SKScene {
    var solarSystemNodes: [SKSpriteNode] = []
    var solarSystems: [SolarSystem] = []
    var selectedNode: SKSpriteNode?
    var visibleLblNode: SKLabelNode?
    var travelBtn: SKSpriteNode = SKSpriteNode()
    override func sceneDidLoad() {
        print("Scene loaded")
        scene?.anchorPoint = CGPoint.zero
        scene?.backgroundColor = Colors.backgroundColor
        createMapNode()
    }
    override func didMove(to view: SKView) {
        print("Did move to view")
        createSolarSystems()
        setupButtonSprites()
    }
    private func createTestingNodes() {
        let node: SKSpriteNode = SKSpriteNode(imageNamed: "")
        node.position = CGPoint(x: mapNode!.size.width / 2, y: mapNode!.size.height / 2)
        node.size = CGSize(width: 100, height: 100)
        mapNode!.addChild(node)
        let node2: SKSpriteNode = SKSpriteNode(imageNamed: "")
        node2.position = CGPoint.zero
        node2.size = CGSize(width: 100, height: 100)
        mapNode!.addChild(node2)
    }
    private func createMapNode() {
        mapNode = SKSpriteNode()
        
        let width = CGFloat(Constants.solarSystemWidth * Constants.universeMaxLong + 2 * Constants.mapOffset)
        let height = CGFloat(Constants.solarSystemHeight * Constants.universeMaxLat + 2 * Constants.mapOffset)
        mapNode!.size = CGSize(width: width, height: height)
        mapNode!.anchorPoint = CGPoint.zero
        mapNode?.zPosition = 1
        addChild(mapNode!)
    }
    private func createSolarSystems() {
        let offset: CGFloat = CGFloat(Constants.mapOffset)
        let xScalar = (mapNode!.frame.width - 2 * offset) / CGFloat(Constants.universeMaxLong)
        let yScalar = (mapNode!.frame.height - 2 * offset) / CGFloat(Constants.universeMaxLat)
        print("Scalars:")
        print(xScalar)
        print(yScalar)
        for solarSystem in solarSystems {
            let imageName = Constants.starTypes.randomElement()
            let solarSystemNode: SKSpriteNode = SKSpriteNode(imageNamed: imageName!)
            let lat: CGFloat = CGFloat(solarSystem.coordinates.latitude)
            let long: CGFloat = CGFloat(solarSystem.coordinates.longitude)
            let pos: CGPoint = CGPoint(x: offset + xScalar * long, y: offset + yScalar * lat)
            
            solarSystemNode.position = pos
            let width = CGFloat(Constants.solarSystemWidth)
            let height = CGFloat(Constants.solarSystemHeight)
            solarSystemNode.size = CGSize(width: width, height: height)
            solarSystemNode.name = solarSystem.name
            solarSystemNodes.append(solarSystemNode)
            mapNode!.addChild(solarSystemNode)
        }
    }
    private func setupButtonSprites() {
        let offset: CGFloat = 20
        let buttonText = SKLabelNode(text: "Travel")
        buttonText.fontColor = Colors.textColor
        let width: CGFloat = 60
        let height: CGFloat = 30
        let size: CGSize = CGSize(width: width, height: height)
        let pos = CGPoint(x: view!.frame.height - width - offset, y: height + offset)
        travelBtn.color = Colors.secondaryColor
        travelBtn.size = size
        travelBtn.position = pos
        travelBtn.zPosition = 2
        travelBtn.addChild(buttonText)
        addChild(travelBtn)
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        if let touch = touches.first {
            let iPos = touch.location(in: self)
            let fPos = touch.previousLocation(in: self)
            let dx = 1.5 * (fPos.x - iPos.x)
            let dy = 1.5 * (fPos.y - iPos.y)
            if let mapNode = mapNode, let view = view {
                var newX = mapNode.position.x - dx
                var newY = mapNode.position.y - dy
                let minX = view.frame.width - mapNode.size.width
                let minY = view.frame.height - mapNode.size.height
                if (newY > 0 || newY < minY) {
                    newY = mapNode.position.y
                }
                if (newX > 0 || newX < minX) {
                    newX = mapNode.position.x
                }
                mapNode.position = CGPoint(x: newX, y: newY)
            }
        }
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        guard let node = nodes(at: touch.location(in: self)).first as? SKSpriteNode else { return }
        if let nodeName = node.name {
            if nodeName.contains("System") {
                if let selectedNode = selectedNode {
                    if selectedNode.name! == nodeName {
                        
                    }
                } else {
                    makeLabel(with: nodeName, node: node)
                }
            }
        }
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { print("no touches"); return }
        guard let node = nodes(at: touch.location(in: self)).first as? SKSpriteNode else { print("no spritenode touched"); return }
        if let nodeName = node.name {
            if nodeName.contains("System") {
                if let selectedNode = selectedNode {
                    
                } else {
                    selectedNode = node;
                }
            }
        } else {
            visibleLblNode?.removeFromParent()
            visibleLblNode = nil
            selectedNode = nil
        }
        print("\nNAME= \(selectedNode?.name)\n")
    }
    
    private func makeLabel(with name: String, node: SKSpriteNode) {
        let systemNameLbl = SKLabelNode(text: name)
        let xPos = node.position.x
        let yPos = node.position.y + node.size.height
        systemNameLbl.position = CGPoint(x: xPos, y: yPos)
        mapNode!.addChild(systemNameLbl)
        if let visibleNode = visibleLblNode {
            visibleNode.removeFromParent()
        }
        visibleLblNode = systemNameLbl
    }
}

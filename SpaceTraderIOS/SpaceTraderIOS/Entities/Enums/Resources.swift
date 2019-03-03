//
//  Resources.swift
//  SpaceTraderIOS
//
//  Created by Rahil Patel on 3/2/19.
//  Copyright © 2019 HackstreetBoys. All rights reserved.
//

import Foundation

enum Resources: Resource, CaseIterable {
    case Water = "Water,0,0,2,30,3,4,Drought,LotsOfWater,Desert,30,50"
    case Furs = "Furs,0,0,0,250,10,10,COLD,RICHFAUNA,LIFELESS,230,280"
    case Food = "Food,1,0,1,100,5,5,CROPFAIL,RICHSOIL,POORSOIL,90,160"
    case Ore = "Ore,2,2,3,350,20,10,WAR,MINERALRICH,MINERALPOOR,350,420"
    case Games = "Games,3,1,6,250,-10,5,BOREDOM,ARTISTIC,Never,160,270"
    case Firearms = "Firearms,3,1,5,1250,-75,100,WAR,WARLIKE,Never,600,1100"
    case Medicine = "Medicine,4,1,6,650,-20,10,PLAGUE,LOTSOFHERBS400,700"
    case Machines = "Machines,4,3,5,900,-30,5,LACKOFWORKERS,Never,Never,600,800 "
    case Narcotics = "Narcotics,5,0,5,3500,-125,150,BOREDOM,WEIRDMUSHROOMS,Never,2000,3000"
    case Robots = "Robots,6,4,7,5000,-150,100,LACKOFWORKERS,Never,Never,3500,5000"
}

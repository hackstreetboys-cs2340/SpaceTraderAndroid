package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Difficulty;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Ship;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Food;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Furs;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Games;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Ore;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Water;

import static org.junit.Assert.*;

public class TestSellItem {

    @Test
    public void sellOneItem() {
        Food food = new Food();
        Water water = new Water();
        Ore ore = new Ore();
        Furs furs = new Furs();
        Games games = new Games();

        Ship ship = new Ship(ShipType.Gnat);
        Player player = new Player("Abby",4,4,4,4, ship, Difficulty.E);
        Planet planet = new Planet("Xerxes");
        planet.generate();
        player.setLocation(planet);

        List<TradeGood> answer = new ArrayList<>();
        answer.add(water);
        answer.add(food);
        answer.add(ore);
        answer.add(games);
        double wallet = player.getWallet();
        water.calculatePrice(planet.getTechLevel(), planet.getResources());
        furs.calculatePrice(planet.getTechLevel(), planet.getResources());
        food.calculatePrice(planet.getTechLevel(), planet.getResources());
        ore.calculatePrice(planet.getTechLevel(), planet.getResources());
        games.calculatePrice(planet.getTechLevel(), planet.getResources());

        player.buy(water);
        player.buy(furs);
        player.buy(food);
        player.buy(ore);
        player.buy(games);

        double walletBefore = wallet - water.getFinalPrice() - furs.getFinalPrice() - food.getFinalPrice() - ore.getFinalPrice() - games.getFinalPrice();

        assertEquals(walletBefore, player.getWallet(),0.01);
        assertEquals(5, player.getShip().getSize());

        player.sell(furs);

        furs.calculatePrice(planet.getTechLevel(), planet.getResources());
        double walletAfter = walletBefore + furs.getFinalPrice();
        assertEquals(walletAfter, player.getWallet(), 0.01);
        assertEquals(answer, player.getShip().getCargoHold());
        assertEquals(4, player.getShip().getSize());
    }
}

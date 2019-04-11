package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.Difficulty;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Ship;
import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

import static org.junit.Assert.*;

public class TestBuyItem {

    @Test
    public void buyOneItem() {
        Ship ship = new Ship(ShipType.Gnat);
        Player player = new Player("Abby",4,4,4,4, ship, Difficulty.E);
        Planet planet = new Planet("Xerxes");
        planet.generate();
        player.setLocation(planet);

        double walletBefore = player.getWallet();
        int sizeBefore = player.getCargoHold().size();

        List<TradeGood> goods = new ArrayList<>();
        for (int i = 0; i < planet.getMarket().getTradeGoods().size(); i++) {
            if (planet.getMarket().getTradeGoods().get(i).getFinalPrice() >= 0) {
                goods.add(planet.getMarket().getTradeGoods().get(i));
            }
        }

        Random rand = new Random();
        int randIndex = rand.nextInt(goods.size());
        TradeGood goodToBuy = goods.get(randIndex);

        player.buy(goodToBuy);
        double walletAfter = walletBefore - goodToBuy.getFinalPrice();
        assertEquals(walletAfter, player.getWallet(),0.01);
        assertEquals(sizeBefore + 1, player.getCargoHold().size());
        assertTrue(player.getCargoHold().contains(goodToBuy));
    }

}

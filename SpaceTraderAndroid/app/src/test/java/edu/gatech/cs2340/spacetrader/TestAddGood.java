package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Ship;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Food;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Furs;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Games;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

import static org.junit.Assert.*;

public class TestAddGood {

    @Test
    public void addGoodNew() {
        Furs fur = new Furs();
        Food food = new Food();
        Games game = new Games();

        List<TradeGood> comp = new ArrayList<>();
        comp.add(fur);
        comp.add(food);
        comp.add(game);

        Ship ship = new Ship();
        ship.add(fur);
        ship.add(food);
        ship.add(game);

        int indexFur = ship.getCargoHold().indexOf(fur);
        int indexFood = ship.getCargoHold().indexOf(food);
        int indexGame = ship.getCargoHold().indexOf(game);

        assertEquals(comp, ship.getCargoHold());
        assertEquals(3, ship.getSize());
        assertEquals(1, ship.getCargoHold().get(indexFur).getQuantity());
        assertEquals(1, ship.getCargoHold().get(indexFood).getQuantity());
        assertEquals(1, ship.getCargoHold().get(indexGame).getQuantity());
    }

    @Test
    public void addGoodExisting() {
        Furs furOne = new Furs();
        Furs furTwo = new Furs();

        List<TradeGood> comp = new ArrayList<>();
        comp.add(furOne);

        Ship ship = new Ship();
        ship.add(furOne);
        ship.add(furTwo);

        int indexFur = ship.getCargoHold().indexOf(furOne);

        assertEquals(comp, ship.getCargoHold());
        assertEquals(2, ship.getSize());
        assertEquals(2, ship.getCargoHold().get(indexFur).getQuantity());
    }

    @Test
    public void negativePrice() {
        Furs fur = new Furs();
        fur.setFinalPrice(0);

        List<TradeGood> comp = new ArrayList<>();

        Ship ship = new Ship();
        ship.add(fur);

        assertEquals(comp, ship.getCargoHold());
        assertEquals(0, ship.getSize());
    }

}

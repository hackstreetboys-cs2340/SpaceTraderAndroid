package edu.gatech.cs2340.spacetrader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.spacetrader.entity.Ship;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Firearms;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Food;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Furs;




public class ShipRemoveGoodJUnitTest {
    private Ship ship;

    @Before
    public void setUp() { ship = new Ship(); }

    @Test
    public void testRemoveOneGood() {
        assertEquals(0, ship.getCargoHold().size());
        Firearms fire = new Firearms();
        Food food = new Food();
        Furs fur = new Furs();

        ship.add(fire);
        ship.add(food);
        ship.add(fur);

        assertEquals(3, ship.getCargoHold().size());

        double temp = 1250.0;

        //assertEquals(temp, ship.remove(fire), 0);


        assertEquals(2, ship.getCargoHold().size());

        String[] expected = new String[ship.getCapacity()];

        expected[0] = "Food";
        expected[1] = "Furs";


        String[] actual = new String[ship.getCapacity()];
        int index = 0;
        for (TradeGood t : ship.getCargoHold()) {
            actual[index] = t.getName();
            index += 1;
        }

        assertArrayEquals(expected, actual);

    }



}

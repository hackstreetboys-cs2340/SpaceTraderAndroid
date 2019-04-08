package edu.gatech.cs2340.spacetrader;

import org.junit.Test;

import edu.gatech.cs2340.spacetrader.entity.CoordinatePair;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;

import static org.junit.Assert.assertEquals;

public class PlayerTravelTest {
    @Test
    public void testVerticalPlanets() {
        Player player = new Player();
        for (int i = 0; i < 100; i++) {
            player.setLocation(new Planet("", new CoordinatePair<>(0, 0)));
            player.getShip().setFuel(10);
            Planet inital = player.getLocation();
            Planet destination = new Planet("", new CoordinatePair<>(0, i));
            boolean expected = i <= 10;
            assertEquals(expected, player.travel(destination));
            if (expected) {
                assertEquals(10 - i, player.getShip().getFuel());
                assertEquals(destination, player.getLocation());
            } else {
                assertEquals(10, player.getShip().getFuel());
                assertEquals(inital, player.getLocation());
            }
        }
    }

    @Test
    public void testHorizontalPlanets() {
        Player player = new Player();
        for (int i = 0; i < 100; i++) {
            player.setLocation(new Planet("", new CoordinatePair<>(0, 0)));
            player.getShip().setFuel(10);
            Planet inital = player.getLocation();
            Planet destination = new Planet("", new CoordinatePair<>(i, 0));
            boolean expected = i <= 10;
            assertEquals(expected, player.travel(destination));
            if (expected) {
                assertEquals(10 - i, player.getShip().getFuel());
                assertEquals(destination, player.getLocation());
            } else {
                assertEquals(10, player.getShip().getFuel());
                assertEquals(inital, player.getLocation());
            }
        }
    }

    @Test
    public void testDiagonalPlanets() {
        Player player = new Player();
        for (int i = 0; i < 100; i++) {
            player.setLocation(new Planet("", new CoordinatePair<>(0, 0)));
            player.getShip().setFuel(10);
            Planet inital = player.getLocation();
            Planet destination = new Planet("", new CoordinatePair<>(i, i));
            int dist = (int)(Math.sqrt(2) * i);
            boolean expected = dist <= 10;
            assertEquals(expected, player.travel(destination));
            if (expected) {
                assertEquals(10 - dist, player.getShip().getFuel());
                assertEquals(destination, player.getLocation());
            } else {
                assertEquals(10, player.getShip().getFuel());
                assertEquals(inital, player.getLocation());
            }
        }
    }

    @Test
    public void testRandomChance() {
        int trials = 10000;
        int buffer = trials / 100; // +/- 1% error
        int chance1 = 0;
        int chance2 = 0;
        for (int i = 0; i < trials; i++) {
            Player player = new Player();
            player.getShip().setFuel(10);
            player.getShip().setHealth(100);
            player.setLocation(new Planet("", new CoordinatePair<Integer, Integer>(0, 0)));
            Planet planet = new Planet("", new CoordinatePair<Integer, Integer>(1, 1));
            player.travel(planet);
            if (player.getShip().getHealth() == 75) {
                chance1++;
            }
            if (player.getShip().getHealth() == 33) {
                chance2++;
            }
        }
        double max1 = 0.1 * trials + buffer;
        double min1 = 0.1 * trials - buffer;
        double max2 = 0.9 * 0.025 * trials + buffer;
        double min2 = 0.9 * 0.025 * trials - buffer;
        assertEquals("First random encounter chance failed: exptected between " + min1 + " and " + max1 + " but was " + chance1 + ".",true, chance1 < max1 && chance1 > min1);
        assertEquals("Second random encounter chance failed: exptected between " + min2 + " and " + max2 + " but was " + chance2 + ".", true, chance2 < max2 && chance1 > min2);
    }

}

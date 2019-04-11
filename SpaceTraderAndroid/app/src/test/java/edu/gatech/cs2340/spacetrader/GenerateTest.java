package edu.gatech.cs2340.spacetrader;

import android.util.Pair;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Market;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Resources;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;

public class GenerateTest {

    @Test
    public void generateMarket() {
        Market newMarket = new Market();

        newMarket.generateMarket();
        System.out.println("Market Test 1" + newMarket);

        newMarket = new Market(TechLevel.getRandomTech(), Resources.getRandomResources());
        newMarket.generateMarket();
        System.out.println("Market Test 2" + newMarket);

    }

    @Test
    public void generatePlanet() {
        Planet newPlanet = new Planet();
        newPlanet.generate();
        System.out.println("Planet Test 1\t" + newPlanet);

        newPlanet = new Planet("One");
        newPlanet.generate();
        System.out.println("Planet Test 2\t" + newPlanet);

        newPlanet = new Planet("Two", new Pair<>(Integer.MAX_VALUE, Integer.MAX_VALUE));
        newPlanet.generate();
        System.out.println("Planet Test 3\t" + newPlanet);
    }

    @Test
    public void generateSystem() {
        List<String> testList = new ArrayList<>();

        SolarSystem newSS = new SolarSystem();
        newSS.generateSystem(3, testList);
        System.out.println("Solar System Test 1\t" + newSS.toString());


        testList.add("One");
        newSS = new SolarSystem();
        newSS.generateSystem(3, testList);
        System.out.println("Solar System Test 2\t" + newSS.toString());

        newSS = new SolarSystem("System 1");
        newSS.generateSystem(3, testList);
        System.out.println("Solar System Test 3\t" + newSS.toString());

        testList.add("Two");
        testList.add("Three");
        newSS = new SolarSystem("System 2");
        newSS.generateSystem(3, testList);
        System.out.println("Solar System Test 4\t" + newSS.toString());

        newSS = new SolarSystem("System 3", new Pair<>(Integer.MAX_VALUE, Integer.MAX_VALUE));
        newSS.generateSystem(3, testList);
        System.out.println("Solar System Test 5\t" + newSS.toString());

    }
}
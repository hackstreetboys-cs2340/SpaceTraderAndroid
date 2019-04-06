package edu.gatech.cs2340.spacetrader;

import android.util.Log;
import android.util.Pair;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

import static org.junit.Assert.*;

public class GenerateTesting {

    @Test
    public void generateMarket() {

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
        Log.d("Solar System Test", "Test 1\t" + newSS.toString());


        testList.add("One");
        newSS = new SolarSystem();
        newSS.generateSystem(3, testList);
        Log.d("Solar System Test", "Test 2\t" + newSS.toString());

        newSS = new SolarSystem("System 1");
        newSS.generateSystem(3, testList);
        Log.d("Solar System Test", "Test 3\t" + newSS.toString());

        testList.add("Two");
        testList.add("Three");
        newSS = new SolarSystem("System 2");
        newSS.generateSystem(3, testList);
        Log.d("Solar System Test", "Test 4\t" + newSS.toString());

        newSS = new SolarSystem("System 3", new Pair<>(Integer.MAX_VALUE, Integer.MAX_VALUE));
        newSS.generateSystem(3, testList);
        Log.d("Solar System Test", "Test 5\t" + newSS.toString());

    }
}

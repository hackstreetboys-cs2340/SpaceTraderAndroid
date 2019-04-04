package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlanetInteractor;

public class PlanetListingViewModel extends AndroidViewModel {

    private PlanetInteractor interactor;
    private SolarSystem currentSystem;
    private Planet currentPlanet;

    public PlanetListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlanetInteractor();
    }

    public void setCurrentSystem(SolarSystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public List<Planet> getPlanets() {
        return interactor.getPlanetsForSystem(currentSystem);
    }
}
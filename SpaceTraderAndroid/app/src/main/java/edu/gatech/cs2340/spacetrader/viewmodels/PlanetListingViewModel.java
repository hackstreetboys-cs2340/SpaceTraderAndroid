package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlanetInteractor;

/**
 * Planet Listing View Model
 */
public class PlanetListingViewModel extends AndroidViewModel {

    private PlanetInteractor interactor;
    private SolarSystem currentSystem;

    /**
     * constructor for planet listing view model
     * @param application app
     */
    public PlanetListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlanetInteractor();
    }

    /**
     * set the currentSystem equal to params
     * @param currentSystem current solar system
     */
    public void setCurrentSystem(SolarSystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    /**
     * get planets for the current system
     * @return list of plants
     */
    public List<Planet> getPlanets() {
        return interactor.getPlanetsForSystem(currentSystem);
    }
}
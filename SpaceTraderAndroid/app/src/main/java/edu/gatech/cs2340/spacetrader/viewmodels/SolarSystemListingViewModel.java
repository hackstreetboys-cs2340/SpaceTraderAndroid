package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SolarSystemInteractor;

/**
 * Solar System Listing View Model Class
 */
public class SolarSystemListingViewModel extends AndroidViewModel {
    private SolarSystemInteractor interactor;
    private Universe currentUniverse;

    /**
     * single argument constructor
     * @param application app
     */
    public SolarSystemListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    /**
     * set the current universe
     * @param currentUniverse current universe
     */
    public void setCurrentUniverse(Universe currentUniverse) {
        this.currentUniverse = currentUniverse;
    }

    /**
     * get the systems for the current universe
     * @return list of systems
     */
    public List<SolarSystem> getSystems() {
        return interactor.getSystemsForUniverse(currentUniverse);
    }
}

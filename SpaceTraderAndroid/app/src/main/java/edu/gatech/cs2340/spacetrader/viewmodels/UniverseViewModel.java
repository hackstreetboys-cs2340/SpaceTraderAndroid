package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.UniverseInteractor;

    public class UniverseViewModel extends AndroidViewModel {

        private UniverseInteractor interactor;

        /**
         * constructor for PlayerViewModel
         *
         * @param application app
         */
        public UniverseViewModel(@NonNull Application application) {
            super(application);
            interactor = Model.getInstance().getUniverseInteractor();
        }

        /**
         * method to add a universe to the app
         * @param universe universe to add
         */
        public void addUniverse(Universe universe) {
            interactor.setMyUniverse(universe);
        }
    }
}

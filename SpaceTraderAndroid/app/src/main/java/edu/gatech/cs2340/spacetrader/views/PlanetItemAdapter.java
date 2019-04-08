package edu.gatech.cs2340.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;

/**
 * Planet Item Adapter
 */
public class PlanetItemAdapter  extends RecyclerView.Adapter<PlanetItemAdapter.PlanetItemViewHolder> {

    List<Planet> planets = new ArrayList<>();
    private OnPlanetClickListener listener;

    @NonNull
    @Override
    public PlanetItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.planet_list_item, viewGroup, false);

        return new PlanetItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetItemViewHolder planetItemViewHolder, int i) {
        Planet planet = planets.get(i);

        Log.d("APP", "Binding: " + i + " " + planets.get(i));

        planetItemViewHolder.planetName.setText(planet.getName());
        planetItemViewHolder.techLevel.setText("Tech: " + planet.getTechLevel().toString());
        planetItemViewHolder.resourceLevel.setText("Resources: " + planet.getResources().toString());
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    /**
     * set list of planets to the list in params
     * @param planets list of planets
     */
    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
        notifyDataSetChanged();
    }

    class PlanetItemViewHolder extends RecyclerView.ViewHolder {
        private TextView planetName;
        private TextView techLevel;
        private TextView resourceLevel;

        public PlanetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            planetName = itemView.findViewById(R.id.text_planet_name);
            techLevel = itemView.findViewById(R.id.text_tech_level);
            resourceLevel = itemView.findViewById(R.id.text_resources);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onPlanetClicked(planets.get(position));
                    }
                }
            });
        }
    }
    public interface OnPlanetClickListener {
        /**
         * listens for a planet in the list to be clicked
         * @param planet planet clicked
         */
        void onPlanetClicked(Planet planet);
    }

    /**
     * sets the on planet click listener
     * @param listener planet click listener
     */
    public void setOnPlanetClickListener(OnPlanetClickListener listener) {
        this.listener = listener;
    }
}

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
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

public class SolarSystemItemAdapter extends RecyclerView.Adapter<SolarSystemItemAdapter.SolarSystemItemViewHolder> {

    private List<SolarSystem> systems = new ArrayList<>();
    private Planet currPlanet;
    private OnSolarSystemClickListener listener;

    @NonNull
    @Override
    public SolarSystemItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.solar_system_list_item, viewGroup, false);

        return new SolarSystemItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SolarSystemItemViewHolder solarSystemItemViewHolder, int i) {
        SolarSystem system = systems.get(i);

        Log.d("APP", "Binding: " + i + " " + systems.get(i));

        //int fuelCostNum = currPlanet.distanceTo(system.getCoordinates());

        solarSystemItemViewHolder.systemName.setText(system.getName());
        solarSystemItemViewHolder.location.setText("(" + system.getCoordinates().first + ", " + system.getCoordinates().second + ")");
    }

    @Override
    public int getItemCount() {
        return systems.size();
    }

    public void setSystems(List<SolarSystem> systems) {
        this.systems = systems;
        notifyDataSetChanged();
    }

    public void setCurrPlanet(Planet currPlanet) {
        this.currPlanet = currPlanet;
        notifyDataSetChanged();
    }

    public SolarSystem getSystem(int position) {
        return systems.get(position);
    }

    class SolarSystemItemViewHolder extends RecyclerView.ViewHolder {
        private TextView systemName;
        private TextView location;
        private TextView fuelCost;

        public SolarSystemItemViewHolder(@NonNull View itemView) {
            super(itemView);
            systemName = itemView.findViewById(R.id.text_solar_system_name);
            location = itemView.findViewById(R.id.text_location);
            fuelCost = itemView.findViewById(R.id.text_fuel_cost);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onSolarSystemClicked(systems.get(position));
                    }
                }
            });
        }
    }

    public interface OnSolarSystemClickListener {
        void onSolarSystemClicked(SolarSystem system);
    }

    public void setOnSolarSystemClickListener(OnSolarSystemClickListener listener) {
        this.listener = listener;
    }

}

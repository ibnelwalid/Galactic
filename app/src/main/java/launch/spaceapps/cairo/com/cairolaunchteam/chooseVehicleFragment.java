package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class chooseVehicleFragment extends Fragment {

    public chooseVehicleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_vehicle, container, false);
        String[] vehicles = {"Atlas V","Vega"};
        ListView vehicle_list = (ListView) rootView.findViewById(R.id.vehicle_choice);
        final ArrayAdapter <String> mAdapter = new ArrayAdapter(getActivity(),
                R.layout.vehicle_choice_listitem,vehicles);
        vehicle_list.setAdapter(mAdapter);
        vehicle_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mAdapter.getItem(position).toString() == "Vega")
                {
                    Toast.makeText(getActivity(), "Check your vehicle",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent MissionSet = new Intent(getActivity(),MissionSetting.class);
                    MissionSet.putExtra("name",mAdapter.getItem(position));
                    startActivity(MissionSet);
                }
            }
        });
        return rootView;
    }
}

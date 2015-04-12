package launch.spaceapps.cairo.com.cairolaunchteam;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class missionSettingFragment extends Fragment {

    public String current_launch_site;
    public missionSettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mission_setting, container, false);
        String name = getActivity().getIntent().getStringExtra("name");
        DataBaseHelper mDataBase = new DataBaseHelper(getActivity());
        Cursor vehicle_Data = mDataBase.getReadableDatabase().query("vehicles",null,"name = '" +
                name +"'",null,null,null,null);
        vehicle_Data.moveToFirst();
        TextView mission_name = (TextView) rootView.findViewById(R.id.vehicle_name);
        mission_name.setText(vehicle_Data.getString(1));
        TextView mission_decription = (TextView) rootView.findViewById(R.id.vehicle_description);
        mission_decription.setText(vehicle_Data.getString(2));
        ArrayList<String> launch_sites = new ArrayList<String>();
        int launch_sites_number = vehicle_Data.getInt(3);
        if(launch_sites_number != 0)
        {
            for(int i = 4;i < launch_sites_number + 4;i++)
            {
                launch_sites.add(vehicle_Data.getString(i));
            }
        }
        final ArrayAdapter <String> mAdapter = new ArrayAdapter (getActivity(),
                android.R.layout.simple_spinner_item,launch_sites);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner launch_sites_spinner = (Spinner) rootView.findViewById(R.id.launch_sites);
        launch_sites_spinner.setAdapter(mAdapter);
        launch_sites_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_launch_site = mAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button check_forecast = (Button) rootView.findViewById(R.id.forecast_check_button);
        check_forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(current_launch_site.equals("Cape Canaveral Florida"))
                {
                    Webdialog forecast = new Webdialog();
                    forecast.url = "http://forecast.weather.gov/MapClick.php?CityName=Cape+Canaveral&state=FL&site=MLB&textField1=28.4047&textField2=-80.605&e=0#.VSo1XqPRbFo";
                    forecast.show(getActivity().getSupportFragmentManager(),null);
                }
                else
                {
                    Webdialog forecast = new Webdialog();
                    forecast.url = "http://forecast.weather.gov/MapClick.php?lat=34.748297617000446&lon=-120.5181691249997&site=all&smap=1#.VSo7t_yUdTt";
                    forecast.show(getActivity().getSupportFragmentManager(),null);
                }
            }
        });
        TextView launch_conditions = (TextView) rootView.findViewById(R.id.launch_conditions);
        launch_conditions.setText(vehicle_Data.getString(7));
        return rootView;
    }
}

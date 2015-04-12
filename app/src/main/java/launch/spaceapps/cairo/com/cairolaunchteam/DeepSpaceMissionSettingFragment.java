package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
* Created by yehya khaled on 4/8/2015.
*/
public class DeepSpaceMissionSettingFragment extends Fragment {

    private String choice;

    public DeepSpaceMissionSettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deep_space_mission_setting, container, false);
        choice = "orbiter";
        final Button orbiter = (Button) rootView.findViewById(R.id.orbiter);
        final Button fly_by = (Button) rootView.findViewById(R.id.fly_by);
        orbiter.setPressed(true);

        DataBaseHelper mHelper = new DataBaseHelper(getActivity());
        String target = getActivity().getIntent().getStringExtra("target");
        String orbiter_query = "type = 'Orbiter' AND planet = '" + target + "'";
        String lander_query = "type = 'FlyBy' AND planet = '" + target + "'";
        String[] columns = {"_id","name","keyname"};
        final Cursor orbiter_data = mHelper.getReadableDatabase().query(
                "missions",columns,orbiter_query,null,null,null,null);
        final Cursor fly_by_data = mHelper.getReadableDatabase().query("missions",columns,lander_query,
                null,null,null,null);
        orbiter_data.moveToFirst();
        //Log.v("cairo_test",orbiter_data.getString(2));
        //Log.v("cairo_test",orbiter_data.getString(1));
        //Log.v("cairo_test",orbiter_data.getString(0));
        final ListView missions = (ListView) rootView.findViewById(R.id.missions);
        final missionsAdapter mAdapter = new missionsAdapter(getActivity(),orbiter_data,0);
        missions.setAdapter(mAdapter);
        missions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) view.findViewById(R.id.item_name);
                String mission_name = name.getText().toString();
                Intent move = new Intent(getActivity(),Mission_data.class);
                move.putExtra("name",mission_name);
                startActivity(move);
            }
        });
        orbiter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v,MotionEvent e) {
                Log.v("cairo test", "orbiter pressed");
                choice = "orbiter";
                fly_by.setPressed(false);
                orbiter.setPressed(true);
                mAdapter.swapCursor(orbiter_data);
                return true;
            }
        });
        fly_by.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                Log.v("cairo test","fly_by pressed");
                choice = "fly_by";
                orbiter.setPressed(false);
                fly_by.setPressed(true);
                mAdapter.swapCursor(fly_by_data);
                return true;
            }
        });
        return rootView;
    }
}


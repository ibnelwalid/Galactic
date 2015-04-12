package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class Mission_dataFragment extends Fragment {

    public Mission_dataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mission_data, container, false);
        Intent mission_name = getActivity().getIntent();
        String name = mission_name.getStringExtra("name");
        DataBaseHelper mHelper = new DataBaseHelper(getActivity());
        final Cursor mission_data = mHelper.getReadableDatabase().query("missions",null,"name = '"+name+"'",
                null,null,null,null);
        if(mission_data.getCount() != 0)
        {
            LinearLayout background = (LinearLayout) rootView.findViewById(R.id.background);
            mission_data.moveToFirst();
            int background_id = getActivity().getResources().getIdentifier(
                    mission_data.getString(4) + "_background","drawable",getActivity().getPackageName());
            background.setBackgroundResource(background_id);
            TextView name_textView = (TextView) rootView.findViewById(R.id.mission_name);
            name_textView.setText(mission_data.getString(1));
            TextView type_textView = (TextView) rootView.findViewById(R.id.mission_type);
            type_textView.setText(mission_data.getString(2));
            TextView target_textView = (TextView) rootView.findViewById(R.id.mission_target);
            target_textView.setText(mission_data.getString(3));
            ImageView mission_icon = (ImageView) rootView.findViewById(R.id.mission_icon);
            int icon_id = getActivity().getResources().
                    getIdentifier(mission_data.getString(4) + "_logo", "drawable", getActivity().getPackageName());
            if(icon_id != 0) {
                mission_icon.setImageResource(icon_id);
            }
            final LinearLayout payload = (LinearLayout) rootView.findViewById(R.id.payload);
            payload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data_dialogue payload_dialogue = new Data_dialogue();
                    payload_dialogue.header = "Payload Data";
                    payload_dialogue.data = mission_data.getString(5);
                    payload_dialogue.show(getActivity().getSupportFragmentManager(),null);
                }
            });
            LinearLayout vehicle = (LinearLayout) rootView.findViewById(R.id.vehicle);
            vehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data_dialogue payload_dialogue = new Data_dialogue();
                    payload_dialogue.header = mission_data.getString(6);
                    payload_dialogue.data = mission_data.getString(7);
                    payload_dialogue.show(getActivity().getSupportFragmentManager(),null);
                }
            });
            LinearLayout launch_site = (LinearLayout) rootView.findViewById(R.id.launch);
            launch_site.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data_dialogue payload_dialogue = new Data_dialogue();
                    payload_dialogue.header = mission_data.getString(8);
                    payload_dialogue.data = mission_data.getString(9);
                    payload_dialogue.show(getActivity().getSupportFragmentManager(),null);
                }
            });
            LinearLayout cost = (LinearLayout) rootView.findViewById(R.id.cost);
            cost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data_dialogue payload_dialogue = new Data_dialogue();
                    payload_dialogue.header = "Mission Cost";
                    payload_dialogue.data = mission_data.getString(10);
                    payload_dialogue.show(getActivity().getSupportFragmentManager(),null);
                }
            });
            LinearLayout timeline = (LinearLayout) rootView.findViewById(R.id.timeline);
            timeline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data_dialogue payload_dialogue = new Data_dialogue();
                    payload_dialogue.header = "Mission Timeline";
                    payload_dialogue.data = mission_data.getString(11);
                    payload_dialogue.show(getActivity().getSupportFragmentManager(),null);
                }
            });
        }
        else
        {
            Log.v("cairo test","no data");
        }
        return rootView;
    }
}

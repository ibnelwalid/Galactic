package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class payloadChoiceFragment extends Fragment {

    public payloadChoiceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payload_choice, container, false);
        final String[] accepted = getActivity().getResources().getStringArray(R.array.payload_choices_accepted);
        final boolean[] accepted_chosen = new boolean[accepted.length];
        for(int i = 0;i < accepted.length;i++)
        {
            accepted_chosen[i] = false;
        }
        final String[] rejected = getActivity().getResources().getStringArray(R.array.payload_choices_rejected);
        final String[] all_data = concat(accepted,rejected);
        final ArrayAdapter<String> mAdapter = new ArrayAdapter(getActivity(),R.layout.payload_choice_listitem,R.id.payload_choice_item,all_data);
        final ListView choices = (ListView) rootView.findViewById(R.id.choice_list);
        choices.setAdapter(mAdapter);
        Button check_payload = (Button) rootView.findViewById(R.id.check_payload);
        check_payload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = choices.getCheckedItemPositions();
                Boolean correct_payload = true;
                for(int i = 0;i < accepted.length;i++)
                {
                    accepted_chosen[i] = false;
                }
                Log.v("cairo test",String.valueOf(checked.size()));
                for(int i = 0;i < checked.size();i++) {
                    Log.v("cairo test", String.valueOf(checked.keyAt(i)));
                    if (checked.valueAt(i)) {
                        String selected = all_data[checked.keyAt(i)];
                        for (String c : rejected) {
                            //Log.v("cairo test",selected);
                            //Log.v("cairo test",c);
                            if (selected.equals(c)) {

                                correct_payload = false;
                                break;
                            }
                        }
                        if (!correct_payload) break;
                        else
                        {
                            accepted_chosen[checked.keyAt(i)] = true;
                            Log.v("cairo test","accepted " + String.valueOf(checked.keyAt(i)));
                        }
                    }
                }
                if(!(correct_payload && accepted_chosen[0] && accepted_chosen [3] && accepted_chosen[4]
                        && accepted_chosen[5]))
                {
                    Toast.makeText(getActivity(), "Check your payload",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.v("cairo test","will select vehicle");
                    Intent vehicleMove = new Intent(getActivity(),chooseVehicle.class);
                    startActivity(vehicleMove);
                }
            }
        });
        return rootView;
    }
    public String[] concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String [] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
}

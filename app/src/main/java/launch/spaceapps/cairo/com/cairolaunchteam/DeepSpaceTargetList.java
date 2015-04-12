package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DeepSpaceTargetList extends Fragment{

    public DeepSpaceTargetList()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.v("cairo test","in on Click");
                    if (v.getTag() != null)
                    {
                        Intent move = new Intent(getActivity(),DeepSpaceMissionSetting.class);
                        move.putExtra("target",v.getTag().toString());
                        startActivity(move);
                    }
            }
        };
        View rootView = inflater.inflate(R.layout.fragment_deep_space_targets, container, false);
        ImageView mercury = (ImageView) rootView.findViewById(R.id.mercury);
        mercury.setOnClickListener(myListener);
        ImageView mars = (ImageView) rootView.findViewById(R.id.mars);
        mars.setOnClickListener(myListener);
        ImageView jupiter = (ImageView) rootView.findViewById(R.id.jupiter);
        jupiter.setOnClickListener(myListener);
        ImageView saturn = (ImageView) rootView.findViewById(R.id.saturn);
        saturn.setOnClickListener(myListener);
        ImageView pluto = (ImageView) rootView.findViewById(R.id.pluto);
        pluto.setOnClickListener(myListener);
        ImageView interstellar = (ImageView) rootView.findViewById(R.id.interstellar);
        interstellar.setOnClickListener(myListener);
        return rootView;
    }


}

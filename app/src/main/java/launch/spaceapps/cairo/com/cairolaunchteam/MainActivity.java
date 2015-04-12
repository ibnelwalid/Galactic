package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Copying Old Database File into System
        DataBaseHelper mHelper = new DataBaseHelper(this);
        try
        {
            String destPath = getApplicationInfo().dataDir + "/databases/" + "data.db";
            Log.v("cairo test","will create file");
            File f = new File(destPath);
            if (f.exists()) {
                Log.v("cairo test", "No Database , Will Copy");
                InputStream in = getApplicationContext().getAssets().open("data.db");
                Log.v("cairo test", "Asset Found!");
                OutputStream out = new FileOutputStream(destPath);
                byte[] buffer = new byte[1024];
                int length;
                while((length = in.read(buffer)) > 0)
                {
                    out.write(buffer);
                }
                in.close();
                out.close();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Log.e("cairo test","could not find asset");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button deep_space = (Button) rootView.findViewById(R.id.deep_space_button);
            Button leo = (Button) rootView.findViewById(R.id.leo_button);
            deep_space.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent going_deep = new Intent(getActivity(),DeepSpaceTargets.class);
                    startActivity(going_deep);
                }
            });
            leo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assemble_Dialogue mdialog = new assemble_Dialogue();
                    mdialog.show(getActivity().getSupportFragmentManager(),null);
                }
            });
            return rootView;
        }
    }
}

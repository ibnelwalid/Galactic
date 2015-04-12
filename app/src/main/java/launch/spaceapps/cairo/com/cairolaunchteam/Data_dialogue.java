package launch.spaceapps.cairo.com.cairolaunchteam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yehya khaled on 4/12/2015.
 */
public class Data_dialogue extends DialogFragment
{
    public String header;
    public String data;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.data_dialogue,null);
        TextView header_text = (TextView) dialog.findViewById(R.id.dialog_header);
        header_text.setText(header);
        TextView data_text = (TextView) dialog.findViewById(R.id.dialog_data);
        data_text.setText(data);
        AlertDialog.Builder mBuilder =
                    new AlertDialog.Builder(getActivity());
        mBuilder.setView(dialog);


        return mBuilder.create();
    }
}

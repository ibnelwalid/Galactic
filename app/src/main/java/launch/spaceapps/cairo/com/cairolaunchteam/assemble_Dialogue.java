package launch.spaceapps.cairo.com.cairolaunchteam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by yehya khaled on 4/12/2015.
 */
public class assemble_Dialogue extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.assemble_dialogue,null);
        Button goAssemble = (Button) dialog.findViewById(R.id.go_assemble);
        goAssemble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payload_choice = new Intent(getActivity(),payloadChoice.class);
                startActivity(payload_choice);
            }
        });
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(dialog);


        return mBuilder.create();
    }
}

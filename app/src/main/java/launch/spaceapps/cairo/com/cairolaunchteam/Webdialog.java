package launch.spaceapps.cairo.com.cairolaunchteam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by yehya khaled on 4/12/2015.
 */
public class Webdialog extends DialogFragment
{

    public String url;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.web_dialogue_layout,null);
        WebView all = (WebView) dialog.findViewById(R.id.all);
        all.loadUrl(url);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(dialog);
        return mBuilder.create();

    }
}

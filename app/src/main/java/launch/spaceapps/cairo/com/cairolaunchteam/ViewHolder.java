package launch.spaceapps.cairo.com.cairolaunchteam;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder
{
    public ImageView item_icon;
    public TextView item_name;
    public ViewHolder(View rootView)
    {
        item_icon = (ImageView) rootView.findViewById(R.id.item_icon);
        item_name = (TextView) rootView.findViewById(R.id.item_name);
    }
}

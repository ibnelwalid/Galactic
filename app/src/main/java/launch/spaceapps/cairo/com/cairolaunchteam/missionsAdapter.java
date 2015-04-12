package launch.spaceapps.cairo.com.cairolaunchteam;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class missionsAdapter extends CursorAdapter
{
    public missionsAdapter(Context context,Cursor cursor,int flags)
    {
        super(context,cursor,flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        View mItem = LayoutInflater.from(context).inflate(R.layout.missions_list_iterm, parent, false);
        ViewHolder mHolder = new ViewHolder(mItem);
        mItem.setTag(mHolder);
        //Log.v(FetchScoreTask.LOG_TAG,"new View inflated");
        return mItem;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        final ViewHolder mHolder = (ViewHolder) view.getTag();
        mHolder.item_name.setText(cursor.getString(1));
        int icon_id = context.getResources().
                getIdentifier(cursor.getString(2) + "_logo","drawable",context.getPackageName());
        if(icon_id != 0) {
            mHolder.item_icon.setImageResource(icon_id);
        }
    }
}


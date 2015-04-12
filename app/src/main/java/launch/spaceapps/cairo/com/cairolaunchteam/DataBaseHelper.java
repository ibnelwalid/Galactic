package launch.spaceapps.cairo.com.cairolaunchteam;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    private final static int DATABASE_VERSION = 1;
    DataBaseHelper(Context context)
    {
        super(context,"data.db",null,DATABASE_VERSION);
        context.openOrCreateDatabase("data.db", context.MODE_PRIVATE, null);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.v("cairo_test","Database Called: " + db.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
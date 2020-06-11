package sg.edu.np.week_6_whackamole_3_0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WhackAMole.db";
    public static final String TABLE_PRODUCTS = "USER";
    public static final String ID = "ID";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_SCORE = "score";


    /*
        The Database has the following properties:
        1. Database name is WhackAMole.db
        2. The Columns consist of
            a. Username
            b. Password
            c. Level
            d. Score
        3. Add user method for adding user into the Database.
        4. Find user method that finds the current position of the user and his corresponding
           data information - username, password, level highest score for each level
        5. Delete user method that deletes based on the username
        6. To replace the data in the database, we would make use of find user, delete user and add user

        The database shall look like the following:

        Username | Password | Level | Score
        --------------------------------------
        User A   | XXX      | 1     |    0
        User A   | XXX      | 2     |    0
        User A   | XXX      | 3     |    0
        User A   | XXX      | 4     |    0
        User A   | XXX      | 5     |    0
        User A   | XXX      | 6     |    0
        User A   | XXX      | 7     |    0
        User A   | XXX      | 8     |    0
        User A   | XXX      | 9     |    0
        User A   | XXX      | 10    |    0
        User B   | YYY      | 1     |    0
        User B   | YYY      | 2     |    0

     */

    private static final String FILENAME = "MyDBHandler.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        /* HINT:
            This is used to init the database.
         */
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /* HINT:
            This is triggered on DB creation.
            Log.v(TAG, "DB Created: " + CREATE_ACCOUNTS_TABLE);
         */

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Level TEXT, Score TEXT)";

        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        /* HINT:
            This is triggered if there is a new version found. ALL DATA are replaced and irreversible.

         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);

    }

    public void addUser(UserData userData)
    {
            /* HINT:
                This adds the user to the database based on the information given.
                Log.v(TAG, FILENAME + ": Adding data for Database: " + values.toString());
             */
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,userData.getMyUserName());
        values.put(COLUMN_PASSWORD,userData.getMyPassword());
        values.put(COLUMN_LEVEL,String.valueOf(userData.getLevels()));
        values.put(COLUMN_SCORE,String.valueOf(userData.getScores()));
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);

        Log.v(TAG, FILENAME + ": Adding data for Database: " + values.toString());

        db.close();


    }

    public UserData findUser(String username) {
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE "
                + COLUMN_USERNAME
                + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        UserData us = new UserData();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            us.setMyUserName(cursor.getString(0));
            us.setMyPassword(cursor.getString(1));
            return us;
        }
        return null;

    } //findProduct



    public boolean deleteAccount(String username) {
        /* HINT:
            This finds and delete the user data in the database.
            This is not reversible.
            Log.v(TAG, FILENAME + ": Database delete user: " + query);
         */
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE "
                + COLUMN_USERNAME + " = \""
                + username+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        UserData user = new UserData();
        if (cursor.moveToFirst()) {
            user.setMyUserName(username);
            db.delete(TABLE_PRODUCTS, COLUMN_USERNAME + " = ?",
                    new String[] { String.valueOf(user.getMyUserName()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;


    }
}

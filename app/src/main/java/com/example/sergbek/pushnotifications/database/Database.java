package com.example.sergbek.pushnotifications.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.sergbek.pushnotifications.model.NotificationEntity;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper implements BaseColumns{

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE = "notification";

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NOTIFICATION_MESSAGE = "notification_message";
    public static final String COLUMN_NOTIFICATION_TITLE = "notification_title";
    public static final String COLUMN_NOTIFICATION_SUBTITLE = "notification_subtitle";
    public static final String COLUMN_NOTIFICATION_TICKER_TEXT = "notification_ticker_text";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + COLUMN_NOTIFICATION_MESSAGE
            + " text, " + COLUMN_NOTIFICATION_TITLE + " text, " + COLUMN_NOTIFICATION_SUBTITLE
            + " text, " + COLUMN_NOTIFICATION_TICKER_TEXT + " text);";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addNotification(NotificationEntity notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTIFICATION_MESSAGE, notification.getMessage());
        values.put(COLUMN_NOTIFICATION_TITLE, notification.getTitle());
        values.put(COLUMN_NOTIFICATION_SUBTITLE, notification.getSubtitle());
        values.put(COLUMN_NOTIFICATION_TICKER_TEXT, notification.getTickerText());

        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    public List<NotificationEntity> getAllNotification() {
        List<NotificationEntity> contactList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NotificationEntity notification = new NotificationEntity();
                notification.setId(cursor.getString(0));
                notification.setMessage(cursor.getString(1));
                notification.setTitle(cursor.getString(2));
                notification.setSubtitle(cursor.getString(3));
                notification.setTickerText(cursor.getString(4));
                contactList.add(notification);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return contactList;
    }

    public void deleteNotification(NotificationEntity notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(notification.getId()) });
        db.close();
    }
}

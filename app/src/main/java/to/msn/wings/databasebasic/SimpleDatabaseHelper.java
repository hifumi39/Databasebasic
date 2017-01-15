package to.msn.wings.databasebasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class SimpleDatabaseHelper extends SQLiteOpenHelper {
    static final private String DBNAME = "sample.sqlite";
    static final private int VERSION = 2;

    public SimpleDatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE produces (" +
                "lotid INTEGER PRIMARY KEY, number INTEGER,comsumer INTEGER,quality TEXT,type TEXT)");

    }

    /*@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books (" +
                "isbn TEXT PRIMARY KEY, title TEXT, price INTEGER)");
        String[] isbns = {"978-4-7980-4512-2",
                "978-4-7741-8030-4", "978-4-7741-8030-4",
                "978-4-7741-7984-1", "978-4-7981-3547-2"};
        String[] titles = {"はじめてのASP.NET Webフォーム",
                "ASP.NET MVC 5実践プログラミング", "Javaポケットリファレンス",
                "Swiftポケットリファレンス", "独習PHP 第3版"};
        int[] prices = {3000, 3500, 2680, 2780, 3200};
        db.beginTransaction();
        try {
            SQLiteStatement sql = db.compileStatement(
                    "INSERT INTO books(isbn, title, price) VALUES(?, ?, ?)");
            for (int i = 0; i < isbns.length; i++) {
                sql.bindString(1, isbns[i]);
                sql.bindString(2, titles[i]);
                sql.bindLong(3, prices[i]);
                sql.executeInsert();
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_v, int new_v) {
        db.execSQL("DROP TABLE IF EXISTS produces");
        onCreate(db);
    }

    public void insertProduce(Produce produce) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("lotid", produce.getLotid());
        values.put("number", produce.getNumber());
        values.put("comsumer", produce.getComsumer());
        values.put("quality", produce.getQuality());
        values.put("type", produce.getType());

        db.insert("produces",null, values);

    }

    public List<Produce> getALL() {
        List<Produce> produceList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("produces", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Produce produce = new Produce();

                produce.setLotid(cursor.getInt(cursor.getColumnIndex("lotid")));
                produce.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                produce.setComsumer(cursor.getInt(cursor.getColumnIndex("comsumer")));
                produce.setQuality(cursor.getString(cursor.getColumnIndex("quality")));
                produce.setType(cursor.getString(cursor.getColumnIndex("type")));

                produceList.add(produce);
            } while (cursor.moveToNext());
        }
        return produceList;
    }

}


package to.msn.wings.databasebasic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SimpleDatabaseHelper helper = null;
    private EditText lotid = null;
    private EditText type = null;
    private EditText quality = null;
    private EditText number = null;
    private EditText comsumer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SimpleDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        /*try {
            Toast.makeText(this, "接続しました",
                    Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }*/

        helper = new SimpleDatabaseHelper(this);
        lotid = (EditText) findViewById(R.id.lotid);
        type = (EditText) findViewById(R.id.type);
        quality = (EditText) findViewById(R.id.quality);
        number = (EditText) findViewById(R.id.number);
        comsumer = (EditText) findViewById(R.id.comsumer);

        Button btnnext = (Button)findViewById(R.id.text);
        if (btnnext == null) {
            Log.w("MainAvtivity","btnnext == null");
            return;
        }
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LIstactivity.class);
                startActivity(intent);
            }
        });
    }

    public void onSave(View view) {
        Produce produce = new Produce();

        produce.setLotid(Integer.parseInt(lotid.getText().toString()));
        produce.setType(Integer.parseInt(type.getText().toString()));
        produce.setQuality(Integer.parseInt(quality.getText().toString()));
        produce.setNumber(Integer.parseInt(number.getText().toString()));
        produce.setComsumer(Integer.parseInt(comsumer.getText().toString()));

        helper.insertProduce(produce);

        Toast.makeText(this, "データの登録に成功しました。", Toast.LENGTH_SHORT).show();
    }

    // updateメソッドを使う場合
    /*public void onSave(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
        ContentValues cv = new ContentValues();
        cv.put("title", txtTitle.getText().toString());
        cv.put("price", txtPrice.getText().toString());
        String[] params = {txtIsbn.getText().toString()};
        db.update("books", cv, "isbn = ?", params);
        Toast.makeText(this, "データの登録に成功しました。",
                Toast.LENGTH_SHORT).show();
                } finally {
            db.close();
        }
    }*/

    public void onDelete(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String[] params = {lotid.getText().toString()};
            db.delete("produces", "lotid = ?", params);
            Toast.makeText(this, "データの削除に成功しました。",
                    Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    public void onSearch(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = null;
        try {
            String[] cols = {"isbn", "title", "price"};
            String[] params = {lotid.getText().toString()};
            cs = db.query("produces", cols, "isbn = ?",
                    params, null, null, null, null);
            if (cs.moveToFirst()) {
                type.setText(cs.getString(1));
                quality.setText(cs.getString(2));
                number.setText(cs.getString(3));
                comsumer.setText(cs.getString(4));
            } else {
                Toast.makeText(this, "データがありません。",
                        Toast.LENGTH_SHORT).show();
            }
        // MoveToFirst ／ MoveToNext メソッドで、結果セットのすべてのデータを読み込む
        /*String msg = "";
        boolean eol = cs.moveToFirst();
        while (eol) {
            msg += cs.getString(1);
            eol = cs.moveToNext();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();*/

        } finally {
            if (cs != null){
                cs.close();
            }
            db.close();
        }
    }
}

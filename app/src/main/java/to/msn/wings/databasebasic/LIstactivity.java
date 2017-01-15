package to.msn.wings.databasebasic;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class LIstactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        ListView listView = (ListView) findViewById(R.id.list_layout);

        SimpleDatabaseHelper dbHelper = new SimpleDatabaseHelper(this);

        listView.setAdapter(new ProduceListAdapter(this,dbHelper.getALL()));

        Button btnCSV = (Button)findViewById(R.id.btn_csv);
        btnCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LIstactivity.this, CsvActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ProduceListAdapter extends ArrayAdapter<Produce> {
        public ProduceListAdapter(Context context, List<Produce> produceList) {
            super(context,R.layout.list_item_produce,produceList);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_produce,null);
            }

            Produce produce = getItem(position);

            if (produce != null) {
                TextView lotid = (TextView)convertView.findViewById(R.id.lotid);
                if (lotid != null) {
                    lotid.setText(Integer.toString(produce.getLotid()));
                }
            }

            if (produce != null) {
                TextView number = (TextView)convertView.findViewById(R.id.number);
                if (number != null) {
                    number.setText(Integer.toString(produce.getNumber()));
                }
            }

            if (produce != null) {
                TextView comsumer = (TextView)convertView.findViewById(R.id.comsumer);
                if (comsumer != null) {
                    comsumer.setText(Integer.toString(produce.getComsumer()));
                }
            }

            if (produce != null) {
                TextView quality = (TextView)convertView.findViewById(R.id.quality);
                if (quality != null) {
                    quality.setText(produce.getQuality());
                }
            }

            if (produce != null) {
                TextView type = (TextView)convertView.findViewById(R.id.type);
                if (type != null) {
                    type.setText(produce.getType());
                }
            }
            Log.d("DEBUG",convertView.toString());

            return convertView;
        }
    }
}

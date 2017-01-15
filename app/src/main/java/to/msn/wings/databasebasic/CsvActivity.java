package to.msn.wings.databasebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CsvActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_csv);

		SimpleDatabaseHelper dbHelper = new SimpleDatabaseHelper(this);
		List<Produce> produceList = dbHelper.getALL();

		StringBuilder strBuilder = new StringBuilder();

		for (Produce produce : produceList) {
			strBuilder.append(produce.toCSVLine());
			strBuilder.append("\n");
		}

		TextView txtCSV = (TextView)findViewById(R.id.txt_csv);
		txtCSV.setText(strBuilder.toString());
	}
}

package sg.edu.rp.c346.p06_taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    int reqCode = 12345;
    Button btnAddTask, btnCancel;
    EditText etName, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etName = (EditText)findViewById(R.id.etName);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                DBHelper db = new DBHelper(AddActivity.this);
                db.insertTask(name, description);
                i.putExtra("name", name);
                setResult(RESULT_OK, i);
                finish();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(AddActivity.this, MyReceiverTask.class);
                intent.putExtra("name", name);
                intent.putExtra("description", description);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        });
    }
}

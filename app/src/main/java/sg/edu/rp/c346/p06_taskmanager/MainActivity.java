package sg.edu.rp.c346.p06_taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAddTask;
    ListView lvTask;
    TaskAdapter ta;
    ArrayList<Task> taskList = new ArrayList<Task>();
    int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int code = 1;
        setContentView(R.layout.activity_main);
        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        lvTask = (ListView)findViewById(R.id.lvTask);
        ta = new TaskAdapter(this, R.layout.row, taskList);
        lvTask.setAdapter(ta);

        displayTask();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, requestCode);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data != null) {
                displayTask();
            }
        }
    }

    public void displayTask() {
        taskList.clear();
        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<Task> task = db.getAllTasks();
        db.close();
        for (int i = 0; i < task.size(); i++){
            taskList.add(new Task(task.get(i).getName(), task.get(i).getDescription()));
        }
        ta.notifyDataSetChanged();
    }
}

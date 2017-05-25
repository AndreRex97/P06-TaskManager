package sg.edu.rp.c346.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017103 on 25/5/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task>{
    private ArrayList<Task> taskList;
    private Context context;
    private TextView tvName, tvDescription;

    public TaskAdapter(Context context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        taskList = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);
        tvName = (TextView) rowView.findViewById(R.id.tvName);

        Task currentTask = taskList.get(position);

        tvName.setText((position+1) + " " + currentTask.getName());
        tvDescription.setText(currentTask.getDescription());

        return rowView;
    }
}

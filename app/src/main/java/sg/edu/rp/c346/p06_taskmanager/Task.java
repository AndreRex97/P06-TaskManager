package sg.edu.rp.c346.p06_taskmanager;

import java.io.Serializable;

/**
 * Created by 15017103 on 25/5/2017.
 */

public class Task implements Serializable{
    private String Name;
    private String Description;

    public Task(String name, String description) {
        Name = name;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}

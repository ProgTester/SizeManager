package com.var_aleks.sizemanager;

import android.os.Environment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private int col = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        directorySizes();
    }

    private void directorySizes() {
        File external_directory = Environment.getExternalStorageDirectory();
        LinearLayout main_layout = (LinearLayout)findViewById(R.id.main_lay);

        for(File file : external_directory.listFiles()) {
            TextView text_view = new TextView(this);
            text_view.setText(file.getName() + " : " +  (directorySize(file) / 1048576) + "");
            main_layout.addView(text_view);
        }
        col += 1;

    }

    private long directorySize(File file) {
        if(file.isFile()) {
            return file.length();
        }
        int sum = 0;

        try {
            if(file.listFiles() != null) {
                for(File cur_file : file.listFiles()) {
                    sum += directorySize(cur_file);
                }
            }
        }
        catch(Exception ex){
            Log.d("TAG", file.listFiles() + "");
            Log.d("TAG", file + "");
            Log.d("TAG", file.length() + "");
        }
        return sum;
    }
}

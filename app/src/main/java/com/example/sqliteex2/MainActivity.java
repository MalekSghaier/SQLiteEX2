package com.example.sqliteex2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDBAdapter dbAdapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner faculties = (Spinner) findViewById(R.id.spinner1);
        final EditText studentName =(EditText) findViewById(R.id.edittext1);
        Button addStudent =(Button) findViewById(R.id.add_student);
        Button deleteEpiTec =(Button) findViewById(R.id.delete_student);
        list =(ListView) findViewById(R.id.listView1);
        dbAdapter=new MyDBAdapter(MainActivity.this);
        dbAdapter.open();
        String[]allFaculties ={"FSEG","ISET-Sousse","ISITCom","ENISO"};
        faculties.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,allFaculties));
        loadList();
        addStudent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dbAdapter.insertStudent(studentName.getText().toString(),faculties.getSelectedItemPosition()+1);

            }
        });
        deleteEpiTec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deleteALLEpiTec();
                loadList();
            }
        });

    }

    private void loadList() {
        ArrayList<String>allStudents =new ArrayList<String>();
        allStudents=dbAdapter.selectAllStudents();
        final  ArrayAdapter<String> adapter =new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,allStudents);
        list.setAdapter(adapter);
    }
}
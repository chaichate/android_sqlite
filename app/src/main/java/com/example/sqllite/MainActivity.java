package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  DataBaseHelper db ;
    private Spinner spinnerCategories ;
    private ListView listviewCategories;
    private EditText editTextNewCategory;
    private Button buttonCategory;
    private List<String> listCategories = new ArrayList<>();
    private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new  DataBaseHelper(this);

        if( db == null)
            db = new DataBaseHelper(this);



        spinnerCategories = (Spinner) findViewById(R.id.spinner_categories);
        listviewCategories = (ListView) findViewById(R.id.todo_listView);
        editTextNewCategory = (EditText) findViewById(R.id.edittextnewcategory);
        buttonCategory = (Button) findViewById(R.id.button_category);

        buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newCategory = editTextNewCategory.getText().toString();


                if (newCategory.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Please Enter Category", Toast.LENGTH_SHORT).show();
                } else {

                    db.addCategory(new Category(newCategory));

                    prepareData();
                    editTextNewCategory.setText("");

                    Toast.makeText(MainActivity.this, "New Category was Successfully to Database", Toast.LENGTH_SHORT).show();
                }

            }
        });

        prepareData();


    }

    public void prepareData(){
        listCategories = db.getAllCategories();
        adapter = new ArrayAdapter<String>(MainActivity.this , android.R.layout.simple_spinner_item , android.R.id.text1 , listCategories);
//        adapter= new ArrayAdapter<String>(MainActivity.this , android.R.layout.simple_spinner_item , listCategories);

        spinnerCategories.setAdapter(adapter);
        listviewCategories.setAdapter(adapter);
    }
}

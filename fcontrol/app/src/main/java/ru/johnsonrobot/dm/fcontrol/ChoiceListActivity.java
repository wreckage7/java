package ru.johnsonrobot.dm.fcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


import static ru.johnsonrobot.dm.fcontrol.R.layout.choicelisr;


/**
 * Created by Den on 10.01.2016.
 */


public class ChoiceListActivity extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(choicelisr);

        ListView complst = (ListView) findViewById(R.id.listView);

        final String[] compnames = new String[] {
                "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
                "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
                "Китти", "Масяня", "Симба"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item	, compnames);//android.R.layout.simple_list_item_1

        complst.setAdapter(adapter);
    }
}

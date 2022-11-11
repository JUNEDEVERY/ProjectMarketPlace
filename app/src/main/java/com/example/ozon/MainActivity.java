package com.example.ozon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AdapterMask pAdapter;
    String Image;
    ListView listView;
    ProgressBar loadingPB;


    public static int keyID;

    public void clickAbout(View v){
        startActivity(new Intent(MainActivity.this, About.class));
    }

    private List<Mask> listProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingPB = findViewById(R.id.loadingPB);

        /*
        Для того чтобы заполнить ListView  нам необходимо создать адптер. Адаптер используется для связи данных (массивы, базы данных)
        со списком (ListView)
        */
        loadingPB.setVisibility(View.VISIBLE);
        ListView ivProducts = findViewById(R.id.ListProduct);//Находим лист в который будем класть наши объекты
        pAdapter = new AdapterMask(MainActivity.this, listProduct); //Создаем объект нашего адаптера
        ivProducts.setAdapter(pAdapter); //Cвязывает подготовленный список с адаптером
        listView = findViewById(R.id.ListProduct);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadingPB.setVisibility(View.INVISIBLE);
                keyID = (int) id;
                Go();
            }
        });
        new GetProducts().execute(); //Подключение к нашей API в отдельном потоке



    }
    public void onGoTOTheADDPage(View v) {
        startActivity(new Intent(this, Addendum.class));
    }
    private class GetProducts extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5001/NGKNN/герасимовна/api/nameofproducts/");//Строка подключения к нашей API
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //вызываем нашу API

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                /*
                BufferedReader успрощает чтение текста из потока символов
                InputStreamReader преводит поток байтов в поток символов
                connection.getInputStream() получает поток байтов
                */
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);//кладет строковое значение в потоке
                }
                return result.toString();


            } catch (Exception exception) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loadingPB.setVisibility(View.VISIBLE);
            try
            {
                JSONArray tempArray = new JSONArray(s);//преоброзование строки в json массив
                for (int i = 0;i<tempArray.length();i++)
                {

                    JSONObject productJson = tempArray.getJSONObject(i);//Преобразование json объекта в нашу структуру
                    Mask tempProduct = new Mask(
                            productJson.getInt("id"),
                            productJson.getString("name"),
                            (float) productJson.getDouble("price"),
                            productJson.getString("weight"),
                            productJson.getString("nameProiz"),
                            productJson.getString("countryProiz"),
                            productJson.getString("picture")

                    );
                    loadingPB.setVisibility(View.INVISIBLE);
                    listProduct.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();

                }
            } catch (Exception ignored) {


            }
        }

    }

    public void Go()
    {
        startActivity(new Intent(this, ChangeActivity.class));
    }
}
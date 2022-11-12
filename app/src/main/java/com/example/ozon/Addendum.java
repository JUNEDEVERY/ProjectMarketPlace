package com.example.ozon;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addendum extends AppCompatActivity implements View.OnClickListener {


    EditText name;
    EditText price;
    EditText weight;
    EditText nameproz;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    EditText country;

    ProgressBar loadingPB;

    String nPicture;
    ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addendum);

        name = findViewById(R.id.etName);
        price = findViewById(R.id.etPrice);
        textView = findViewById(R.id.tvName);
        textView2 = findViewById(R.id.tvPrice);
        textView3 = findViewById(R.id.tvWeight);
        textView4 = findViewById(R.id.tvBrand);
        textView5 = findViewById(R.id.tvstrana);
        weight = findViewById(R.id.etWeight);
        nameproz = findViewById(R.id.etManufactursName);
        country = findViewById(R.id.tvcountry);
        picture = findViewById(R.id.nonephotoItem);
        loadingPB = findViewById(R.id.loadingPB);

        Button buttonChange = findViewById(R.id.buttonChange);
        buttonChange.setOnClickListener(this);
        picture.setOnClickListener(this);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (name.getText().toString().isEmpty()) {
                    textView.setVisibility(View.INVISIBLE);
                } else if (!name.getText().toString().isEmpty()) {
                    textView.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (price.getText().toString().isEmpty()) {
                    textView2.setVisibility(View.INVISIBLE);
                } else if (!price.getText().toString().isEmpty()) {
                    textView2.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (weight.getText().toString().isEmpty()) {
                    textView3.setVisibility(View.INVISIBLE);
                } else if (!weight.getText().toString().isEmpty()) {
                    textView3.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        nameproz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nameproz.getText().toString().isEmpty()) {
                    textView4.setVisibility(View.INVISIBLE);
                } else if (!nameproz.getText().toString().isEmpty()) {
                    textView4.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });




        country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (country.getText().toString().isEmpty()) {
                    textView5.setVisibility(View.INVISIBLE);
                } else if (!country.getText().toString().isEmpty()) {
                    textView5.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        if (name.getText().toString().equals("")) {
            name.setHint("Имя товара");
        } else {
            name.setHint("Имя товара");
        }

        if (price.getText().toString().equals("")) {
            price.setHint("Цена товара");
        } else {
            price.setHint("Цена товара");
        }

        if (weight.getText().toString().equals("")) {
            weight.setHint("Вес товара");
        } else {
            weight.setHint("Вес товара");
        }

        if (nameproz.getText().toString().equals("")) {
            nameproz.setHint("Производитель");
        } else {
            nameproz.setHint("Производитель");
        }

        if (country.getText().toString().equals("")) {
            country.setHint("Страна производителя");
        } else {
            country.setHint("Страна производителя");
        }


    }

    public final ActivityResultLauncher<Intent> pickImg = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            InputStream is = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            picture.setImageBitmap(bitmap);
                            nPicture = encodeImage(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    public void onClick3(View v) {
        startActivity(new Intent(this, Addendum.class));
    }

    public void onClick2(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }


    private void postCreate(String Name, String Price, String Weight, String NameProiz, String CountryProiz, String Image) {

        loadingPB.setVisibility(View.VISIBLE);


        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://ngknn.ru:5001/NGKNN/ГерасимовНА/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
            DataModal modal = new DataModal(Name, Float.parseFloat(Price), Weight, NameProiz, CountryProiz, Image);
            Call<DataModal> call = retrofitAPI.createPost(modal);
            call.enqueue(new Callback<DataModal>() {
                @Override
                public void onResponse(Call<DataModal> call, Response<DataModal> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(Addendum.this, "Во время добавления что-то пошло не по плану. Мы уже разбираемся", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(Addendum.this, "Товар успешно добавлен", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    weight.setText("");
                    price.setText("");
                    nameproz.setText("");
                    country.setText("");
                    loadingPB.setVisibility(View.INVISIBLE);


                }

                @Override
                public void onFailure(Call<DataModal> call, Throwable t) {
                    Toast.makeText(Addendum.this, "Еще секундочку....: " + t.getMessage(), Toast.LENGTH_LONG).show();


                }
            });
        }
        catch (Exception e) {
            Toast.makeText(Addendum.this, "Что-то пошло не так в процессе добавления: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void onClickAbout(View v) {
        {
            startActivity(new Intent(this, About.class));
        }

    }

    public static String encodeImage(Bitmap bitmap) {
        int prevW = 500;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();

        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return java.util.Base64.getEncoder().encodeToString(bytes);
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonChange: {

                if (name.getText().length() == 0 || price.getText().length() == 0 || weight.getText().length() == 0 || nameproz.getText().length() == 0 || country.getText().length() == 0) {
                    Toast.makeText(this, "Возможно одно или несколько полей были незаполнены", Toast.LENGTH_LONG).show();
                    return;
                }
                String Name = name.getText().toString();
                String Price = price.getText().toString();
                String Weight = weight.getText().toString();
                String Nameproz = nameproz.getText().toString();
                String Country = country.getText().toString();

                postCreate(Name, Price, Weight, Nameproz, Country, nPicture);
                new Handler().postDelayed(() -> startActivity(
                        new Intent(Addendum.this, MainActivity.class)), 1000);


            }
            break;

            case R.id.nonephotoItem:

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImg.launch(intent);
                break;

        }
    }


}

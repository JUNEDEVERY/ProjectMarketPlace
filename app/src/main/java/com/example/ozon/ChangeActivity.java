package com.example.ozon;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener {


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
    Button buttonDel;
    Button buttonChange;
    Button btndeletePicture;
    ProgressBar loadingPB;

    String nPicture;
    ImageView picture;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bitmap bitmap = null;
                    ImageView imageView = (ImageView) findViewById(R.id.nonephotoItem);
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri selectedImage = result.getData().getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageView.setImageBitmap(null);
                        imageView.setImageBitmap(bitmap);
                        TextView deletePicture = findViewById(R.id.buttonChange);
                        deletePicture.setVisibility(View.VISIBLE);
                        nPicture = BitMapToString(bitmap);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change2);

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
        buttonDel = findViewById(R.id.btnDelete);
        btndeletePicture = findViewById(R.id.buttonChange);
        buttonChange = findViewById(R.id.btnChange);
        loadingPB = findViewById(R.id.loadingPB);

        buttonDel.setOnClickListener(this);
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

        showAll();


    }

    private void showAll() {
        loadingPB.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/ngknn/герасимовна/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<DataModal> call = retrofitAPI.getDATA(MainActivity.keyID);
        call.enqueue(new Callback<DataModal>() {

            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ChangeActivity.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
                }
                name.setText(response.body().getName());

                price.setText(response.body().getPrice().toString());
                weight.setText(response.body().getWeight());
                nameproz.setText(response.body().getNameProiz());
                country.setText(response.body().getCountryProiz());
                nPicture = response.body().getImage();
                loadingPB.setVisibility(View.INVISIBLE);

                if (response.body().getImage() == null) {
                    picture.setImageResource(R.drawable.nullphoto);
                    btndeletePicture.setVisibility(View.INVISIBLE);
                } else {
                    Bitmap bitmap = StringToBitMap(response.body().getImage());
                    picture.setImageBitmap(bitmap);
                    btndeletePicture.setVisibility(View.VISIBLE);
                }


//
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                Toast.makeText(ChangeActivity.this, "При выводе данных возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();
                loadingPB.setVisibility(View.INVISIBLE);
            }
        });
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void nonePicture(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        someActivityResultLauncher.launch(photoPickerIntent);

    }
    public void onClickAbout(View v){
        {
            startActivity(new Intent(this, About.class));
        }

    }
    public void onClick3(View v) {
        startActivity(new Intent(this, Addendum.class));
    }

    public void onClick2(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void buttonDeletePhoto(View v) {
        ImageView picture = (ImageView) findViewById(R.id.nonephotoItem);
        picture.setImageBitmap(null);
        nPicture = null;
        TextView deletePicture = findViewById(R.id.buttonChange);
        picture.setImageResource(R.drawable.nullphoto);
        deletePicture.setVisibility(View.INVISIBLE);

    }


    private void getChangeRow(String Name, String Price, String Weight, String NameProiz, String CountryProiz, String Image) {

        loadingPB.setVisibility(View.VISIBLE);
        buttonDel.setVisibility(View.INVISIBLE);
        btndeletePicture.setVisibility(View.INVISIBLE);
        buttonChange.setVisibility(View.INVISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/ГерасимовНА/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        DataModal modal = new DataModal(Name, Float.parseFloat(Price), Weight, NameProiz, CountryProiz, Image);
        Call<DataModal> call = retrofitAPI.updateData(MainActivity.keyID, modal);
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(ChangeActivity.this, "При изменение данных возникла ошибка", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ChangeActivity.this, "Данные изменены", Toast.LENGTH_SHORT).show();

                loadingPB.setVisibility(View.INVISIBLE);
                buttonDel.setVisibility(View.VISIBLE);
                btndeletePicture.setVisibility(View.VISIBLE);
                buttonChange.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                Toast.makeText(ChangeActivity.this, "При изменение записи возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
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

    public static Bitmap getImgBitmap(Context context, String encodedImg) {
        if (!encodedImg.equals("null")) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = java.util.Base64.getDecoder().decode(encodedImg);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }

        return BitmapFactory.decodeResource(context.getResources(), R.drawable.nullphoto);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChange: {

                if (name.getText().length() == 0 || price.getText().length() == 0 || weight.getText().length() == 0 || nameproz.getText().length() == 0 || country.getText().length() == 0) {
                    Toast.makeText(this, "Возможно одно или несколько полей были незаполнены", Toast.LENGTH_LONG).show();
                    return;
                }

                String Name = name.getText().toString();
                String Price = price.getText().toString();
                String Weight = weight.getText().toString();
                String Nameproz = nameproz.getText().toString();
                String Country = country.getText().toString();

                getChangeRow(Name, Price, Weight, Nameproz, Country, nPicture);
                new Handler().postDelayed(() -> startActivity(
                        new Intent(ChangeActivity.this, MainActivity.class)), 1000);


            }
            break;
            case R.id.btnDelete:

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://ngknn.ru:5001/NGKNN/ГерасимовНА/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                Call call = retrofitAPI.deleteData(MainActivity.keyID);
                call.enqueue(new Callback<DataModal>() {
                    @Override
                    public void onResponse(Call<DataModal> call, Response<DataModal> response) {

                        if (!response.isSuccessful()) {
                            Toast.makeText(ChangeActivity.this, "При удание записи возникла ошибка", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(ChangeActivity.this, "Удаление прошло успешно", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(() -> startActivity(
                                new Intent(ChangeActivity.this, MainActivity.class)), 1000);
                    }

                    @Override
                    public void onFailure(Call<DataModal> call, Throwable t) {
                        Toast.makeText(ChangeActivity.this, "При удаление записи возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

                break;

            case R.id.nonephotoItem:

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImg.launch(intent);
                break;

        }
    }


}

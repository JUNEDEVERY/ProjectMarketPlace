package com.example.ozon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterMask  extends BaseAdapter {
    private Context mContext;
    List<Mask> maskList;

    public AdapterMask(Context mContext, List<Mask> listProduct) {
        this.mContext = mContext;
        this.maskList = listProduct;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return maskList.get(i).getID();
    }

    private Bitmap getUserImage(String encodedImg)
    {

        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
        {
            return null;

        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = View.inflate(mContext,R.layout.item_layuot,null);
        TextView Title = v.findViewById(R.id.txtTitle);
        TextView Price = v.findViewById(R.id.txtprice);
        TextView Weight = v.findViewById(R.id.txtweight);
        TextView NameProiz = v.findViewById(R.id.txtnameProiz);
        TextView Country = v.findViewById(R.id.txtCountry);
        ImageView Image = v.findViewById(R.id.nonephotoItem);

        Mask mask = maskList.get(i);
        Title.setText(mask.getName());
        Price.setText(Float.toString(mask.getPrice()).toString());
        Weight.setText(mask.getWeight());
        NameProiz.setText(mask.getNameProiz());
        Country.setText(mask.getCountryProiz());
        if(mask.getImage().toString().equals("null"))
        {
            Image.setImageResource(R.drawable.nullphoto);
        }
        else
        {
            Image.setImageBitmap(getUserImage(mask.getImage()));
        }


        return v;
    }
}


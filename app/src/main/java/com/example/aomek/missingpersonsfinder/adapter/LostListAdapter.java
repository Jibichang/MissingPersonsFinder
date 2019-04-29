package com.example.aomek.missingpersonsfinder.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Call;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.retrofit.DownloadImageFromInternet;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.gms.flags.impl.DataUtils;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LostListAdapter extends ArrayAdapter<Lost>{
    private Context mContext;
    private int mResource;
    private List<Lost> mLostItemList;
    ImageView pathImageView;

    public LostListAdapter(@NonNull Context context,
                           int resource,
                           @NonNull List<Lost> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mLostItemList = objects;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, parent, false);

        TextView nameTextView = view.findViewById(R.id.edittext_list_name);
        TextView detailTextView = view.findViewById(R.id.edittext_list_detail);
        TextView dateTextView = view.findViewById(R.id.edittext_list_date);
        ImageView genderImageView = view.findViewById(R.id.imgView_list_gender);
        ImageView ageImageView = view.findViewById(R.id.imgView_list_age);
        TextView cityTextView = view.findViewById(R.id.edittext_list_city);
        ImageView statusImageView = view.findViewById(R.id.imgView_list_status);
        pathImageView = view.findViewById(R.id.image_list_lost);
        TextView simText = view.findViewById(R.id.text_sim);

        Lost lostItem = mLostItemList.get(position);
        String Fname = lostItem.getFname();
        String Lname = lostItem.getLname();
        String Detail = lostItem.getDetailEtc();
        String Date = lostItem.getRegDate();
        String Gender = lostItem.getGender();
        String Age = lostItem.getAge();
        String City = lostItem.getCity();
        String Status = lostItem.getStatus();
        String Path = lostItem.getPathImg();
        String Sim = lostItem.getSim();

        String name = Fname + "  " + Lname;
        nameTextView.setText(name);
        detailTextView.setText(Detail);
        dateTextView.setText(Date);
        cityTextView.setText(City);
        simText.setText(Sim);

        if (!Path.isEmpty() && !Path.equals("pic0")){
            String imgURL = Lost.getBASE_URL()+ "/plost/api/imgupload/" + Path;

            new DownloadImageFromInternet(pathImageView).execute(imgURL);
//            pathImageView.setImageBitmap(selectableItem.getImage());
        }

        if (Gender.equals("M")) {
            genderImageView.setImageResource(R.drawable.icons8male);
        } else if (Gender.equals("F")) {
            genderImageView.setImageResource(R.drawable.icons8female);
        } else {
            genderImageView.setImageResource(R.drawable.icons8no2);
        }

        switch (Age) {
            case "0-10":
                ageImageView.setImageResource(R.drawable.icons8kid);
                break;
            case "11-15":
                ageImageView.setImageResource(R.drawable.icons8kid);
                break;
            case "60+":
                ageImageView.setImageResource(R.drawable.icons8old);
                break;
            default:
                ageImageView.setImageResource(R.drawable.icons8adult);
                break;
        }

        if (Status.equals("1")){
            statusImageView.setImageResource(R.drawable.icons8find);
        }

        return view;
    }


}

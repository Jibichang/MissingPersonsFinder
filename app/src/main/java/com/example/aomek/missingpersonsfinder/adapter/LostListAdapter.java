package com.example.aomek.missingpersonsfinder.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.model.Lost;

import java.util.List;

public class LostListAdapter extends ArrayAdapter<Lost>{
    private Context mContext;
    private int mResource;
    private List<Lost> mLostItemList;

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

        Lost lostItem = mLostItemList.get(position);
        String Fname = lostItem.getFname();
        String Lname = lostItem.getLname();
        String Detail = lostItem.getDetailEtc();
        String Date = lostItem.getRegDate();
        String Gender = lostItem.getGender();

        String name = Fname +"  "+ Lname;
        nameTextView.setText(name);
        detailTextView.setText(Detail);
        dateTextView.setText(Date);

        if (Gender.equals("M")){
            genderImageView.setImageResource(R.drawable.icons8male);
        }else if (Gender.equals("F")){
            genderImageView.setImageResource(R.drawable.icons8female);
        }else {
            genderImageView.setImageResource(R.drawable.icons8adult);
        }

//        titleTextView.setText(title);
//        numberTextView.setText(number);

//        AssetManager am = mContext.getAssets();
//        try {
//            InputStream is = am.open(filename);
//            Drawable drawable = Drawable.createFromStream(is, "");
//            imageView.setImageDrawable(drawable);
//        } catch (IOException e) {
//            File privateDir = mContext.getFilesDir();
//            File logoFile = new File(privateDir, filename);
//
//            Bitmap bitmap = BitmapFactory.decodeFile(logoFile.getAbsolutePath(), null);
//            imageView.setImageBitmap(bitmap);
//
//            e.printStackTrace();
//        }

        return view;
    }
}

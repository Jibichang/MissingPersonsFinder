package com.example.aomek.missingpersonsfinder.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aomek.missingpersonsfinder.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.ViewHolder> implements ItemClickListener{
    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames;
    private ArrayList<Integer> mImageUrls;
    private ArrayList<String> mCodes;
    private Context mContext;
    private int selected = -1;

    public DetailListAdapter(Context context, ArrayList<String> names, ArrayList<Integer> imageUrls,ArrayList<String> code) {
        mNames = names;
        mImageUrls = imageUrls;
        mCodes = code;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
        if (selected != position) {
            holder.name.setTextColor(Color.parseColor("#000000"));
        }else {
            holder.name.setTextColor(holder.name.getResources().getColor(R.color.blue));
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.name.setTextColor(view.getResources().getColor(R.color.blue));
                if (selected == -1){
                    selected = position;
                }else{
                    if (selected != position){
                        notifyItemChanged(selected);
                        selected = position;
                        notifyItemChanged(selected);

                    }
                    else {
                        selected = position;
                        notifyItemChanged(selected);
//                        holder.name.setTextColor(view.getResources().getColor(R.color.black_de));
                    }
                }
                String Code = mCodes.get(position);
                String Name = mNames.get(position);


                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                getTypeData(Code, Name);

                Snackbar.make(view, mNames.get(position), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }

    public void getTypeData(String code, String name){
        String substring = code.substring(0, 1);
        switch (substring){
            case "H":
                selectableItem.setHairtype(code);
                selectableItemDetail.setHairtype(name);
                break;
            case "S":
                selectableItem.setShape(code);
                selectableItemDetail.setShape(name);
                break;
            case "U":
                selectableItem.setUpperwaist(code);
                selectableItemDetail.setUpperwaist(name);
                break;
            case "L":
                selectableItem.setLowerwaist(code);
                selectableItemDetail.setLowerwaist(name);
                break;
        }
    }

    public void setSelected(int selected) {
        this.selected = selected;
        getTypeData(mCodes.get(selected), mNames.get(selected));
    }
}

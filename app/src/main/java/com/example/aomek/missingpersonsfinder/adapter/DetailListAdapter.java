package com.example.aomek.missingpersonsfinder.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aomek.missingpersonsfinder.R;

import java.util.ArrayList;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames;
    private ArrayList<Integer> mImageUrls = new ArrayList<Integer>();
    private Context mContext;
    private int selectedPosition = -1;
    private ItemClickListener onItemClickListener;

    public DetailListAdapter(Context context, ArrayList<String> names, ArrayList<Integer> imageUrls) {
        mNames = names;
        mImageUrls = imageUrls;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
//        holder.name.setTextColor(Color.RED);

//        holder.checkBox.setChecked(selectedPosition == position);
//        if(selectedPosition == position){
//            holder.checkBox.setChecked(true);
//        }
//        else{
//            holder.checkBox.setChecked(false);
//        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CardView cardView = view.findViewById(R.id.CV1);
//                int color = R.color.colorPrimary;
//                view.setForeground(new ColorDrawable(ContextCompat.getColor(mContext, color)));
                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
//        holder.getAdapterPosition();

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        CheckBox checkBox;
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }

//    public void setChecked(boolean value) {
//        if (value) {
//            textView.setBackgroundColor(Color.LTGRAY);
//        } else {
//            textView.setBackground(null);
//        }
//        mItem.setSelected(value);
//        textView.setChecked(value);
//    }
//
//    public interface OnItemSelectedListener {
//        void onItemSelected(SelectableItem item);
//    }
        public void setItemClickListener(ItemClickListener clickListener) {
            onItemClickListener = clickListener;
        }



}

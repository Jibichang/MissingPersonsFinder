package com.example.aomek.missingpersonsfinder.ui.findandselect;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.home.HomeActivity;
import com.example.aomek.missingpersonsfinder.home.MainActivity;

public class FindAndSelectFragment extends Fragment {

    private static final String TAG = "FindAndSelectFragment";
    private Button btnNavFrag1;

    private FindAndSelectViewModel mViewModel;

    public static FindAndSelectFragment newInstance() {
        return new FindAndSelectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_main, container, false);

//        btnNavFrag1 = (Button) view.findViewById(R.id.button2);
//        Log.d(TAG, "onCreateView: started.");
//
//        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Going to Fragment 1", Toast.LENGTH_SHORT).show();
//
////                ((HomeActivity)getActivity()).setViewPager(0);
//            }
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FindAndSelectViewModel.class);
        // TODO: Use the ViewModel
    }



}

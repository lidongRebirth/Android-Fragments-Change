package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus tp on 2018/5/6.
 */

public class BodyPartFragment extends Fragment {

    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";


    private static final String TAG = "BodyPartFragment";
    private List<Integer> mImageIds;
    private int mListIndex;

    public BodyPartFragment() {
        super();
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {

        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        //先来判断其是否为空，用来获取上次保存的信息
        if(savedInstanceState!=null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container,false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_imageview);
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));
        if(mImageIds!=null){                                        //此处实现当点击人身体时也能够改变图像
            imageView.setImageResource(mImageIds.get(mListIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListIndex<mImageIds.size()-1){
                        mListIndex++;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }
        else{
            Log.i(TAG, "图像列表为空");
        }

        return rootView;
    }
    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }
}

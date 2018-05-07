package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * 用来展现全部图片的Fragment
 */

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallback;
    //定义接口
    public interface OnImageClickListener{      //接口将在HostActivity中实现
        void onImageSelected(int position);//触发
    }

    @Override
    public void onAttach(Context context) {//在此方法中检查 OnImageClickListener是否实现
        super.onAttach(context);
        try{
            mCallback= (OnImageClickListener) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    +"must implent OnImageClickListener");
        }
    }
    public MasterListFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.image_grid_view);
        MasterListAdapter madapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(madapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallback.onImageSelected(position);//点击图片，调用该方法，此接口方法在MainActivity中实现
            }
        });

        return rootView;
    }
}

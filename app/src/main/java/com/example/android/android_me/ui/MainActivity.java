package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * HostActivity
 */
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    //头、身体、腿各12个头像
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.android_me_linear_layout)!=null){      //判断是单窗体还是多窗体
            //刚启动主页面若是含有android_me_linear_layout布局则说明是多窗体，用的是layout-sw600dp的主布局
            mTwoPane=true;
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.image_grid_view);
            gridView.setNumColumns(2);
            if (savedInstanceState==null) {
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                headFragment.setmListIndex(1);
                FragmentManager fragmentManager = getSupportFragmentManager();//启动一个新的Fragment事物
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                BodyPartFragment bodyPartFragment = new BodyPartFragment();
                bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                bodyPartFragment.setmListIndex(1);
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyPartFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                legFragment.setmListIndex(1);
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        }else {
            mTwoPane=false;
        }

    }

    @Override
    public void onImageSelected(int position) {         //在MasterListFragment中触发，在HostActivity中实现 实现的是接口
        Toast.makeText(this,"Position clicked"+position,Toast.LENGTH_SHORT).show();
        int bodyPartNumber =position/12;    //判断是头还是身体还是腿
        int listIndex=position-12*bodyPartNumber;
        if(mTwoPane){
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber){
                case 0:
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container,newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container,newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container,newFragment)
                            .commit();
                    break;
                default:
                    break;
            }

        }else {
            switch (bodyPartNumber){
                case 0:
                    headIndex=listIndex;
                    break;
                case 1:
                    bodyIndex=listIndex;
                    break;
                case 2:
                    legIndex=listIndex;
                    break;
                default:
                    break;
            }
            Bundle bundle=new Bundle();//传递给AndroidMeActivity
            bundle.putInt("headIndex",headIndex);
            bundle.putInt("bodyIndex",bodyIndex);
            bundle.putInt("legIndex",legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }





    }
}

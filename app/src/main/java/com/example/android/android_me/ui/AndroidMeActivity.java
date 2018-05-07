/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * 当为手机屏幕时的点击按钮出现的活动，来显示整个小人
 */
// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    //头、身体、腿各12个头像
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        headIndex = getIntent().getIntExtra("headIndex", 0);
        bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
        legIndex = getIntent().getIntExtra("legIndex", 0);


        if (savedInstanceState==null) {
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(headIndex);
            FragmentManager fragmentManager = getSupportFragmentManager();//启动一个新的Fragment事物
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyPartFragment.setmListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyPartFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setmImageIds(AndroidImageAssets.getLegs());
            legFragment.setmListIndex(legIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }


    }
}

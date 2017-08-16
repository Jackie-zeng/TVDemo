/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.user.tvdemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private RecyclerView mRecyclerView;
    private List<String> mDataList = new ArrayList<>();

    {
        for (int i = 0; i < 20; i++) {
            mDataList.add("msg"+(i+1));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerAdapter adapter = new RecyclerAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.setFocusable(true);
        button1.setBackgroundResource(R.drawable.foucus_background);
        button2.setBackgroundResource(R.drawable.foucus_background);
        button3.setBackgroundResource(R.drawable.foucus_background);
        button4.setBackgroundResource(R.drawable.foucus_background);
        button1.requestFocus();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1,null);
            return new ViewHolders(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ViewHolders viewHolder = (ViewHolders) holder;
            viewHolder.textView.setText(mDataList.get(position));
            RecyclerView.LayoutParams p = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            viewHolder.textView.setLayoutParams(p);
            viewHolder.textView.setGravity(Gravity.CENTER);

            viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    float bottom = v.getBottom();
                    float top = v.getTop();
                    int height = mRecyclerView.getHeight();
                    if (position!=0&&position!=mDataList.size()-1&&bottom<=height-30&&top>10){

                        if (v.hasFocus()) {
                            ViewCompat.animate(v).scaleX(1.5f).scaleY(1.5f).translationZ(2).start();
                        } else {
                            ViewCompat.animate(v).scaleX(1f).scaleY(1f).translationZ(0).start();
                        }

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    private class ViewHolders extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolders(View itemView) {
            super(itemView);

            itemView.setFocusable(true);
            itemView.setBackgroundResource(R.drawable.foucus_background);
            textView = (TextView) itemView.findViewById(android.R.id.text1);

        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("MainActivity: onKeyDown", keyCode+"");
        View v = getCurrentFocus();
        if (v!=null){
            switch (v.getId()){
                case R.id.button1:
                    showToast("button1");

                    break;
                case R.id.button2:
                    showToast("button2");

                    break;
                case R.id.button3:
                    showToast("button3");
                    break;
                case R.id.button4:
                    showToast("button4");
                    break;
                case R.id.recycler:
                  //  View chlid =
                    showToast("recycler");
                     break;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private void showToast(String which){
        String msg = "current focus view is_";
        Toast.makeText(this,msg+which,Toast.LENGTH_SHORT).show();
    }



}

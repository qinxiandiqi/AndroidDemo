package com.qinxiandiqi.androiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jianan on 2017/1/17.
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {

    private RecyclerView rvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(new Adapter());
    }

    class Adapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            return new Holder(textView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.setText(position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public Holder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView;
        }

        public void setText(int position){
            tvItem.setText("position:" + position);
        }
    }
}

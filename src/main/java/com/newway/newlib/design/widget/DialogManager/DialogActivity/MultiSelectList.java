package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.newway.newlib.R;
import com.newway.newlib.design.widget.view.CheckBoxStyleable;
import com.newway.newlib.innerClasses.Tools.MyTools;


public class MultiSelectList extends DialogActivity {

    RecyclerView mRecyclerView;
    boolean[] mCheckListValues;
    String[] mItemText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init(Bundle SavedBundle) {
        super.init(SavedBundle);
        mItemText = getIntent().getExtras().getStringArray("item_texts");
        if (SavedBundle == null)
            mCheckListValues = getIntent().getExtras().getBooleanArray("default_item_states");
        else
            mCheckListValues = SavedBundle.getBooleanArray("checked_items");

        mRecyclerView = new RecyclerView(this);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams((int) getResources().getDimension(R.dimen.dialog_activity_min_width), ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView.setLayoutParams(params);
        mFrameLayout.addView(mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(new Adapter());


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBooleanArray("checked_items", mCheckListValues);
        super.onSaveInstanceState(outState);
    }


    class Adapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CheckBoxStyleable checkBoxStyleable = new CheckBoxStyleable(MultiSelectList.this);
            parent.addView(checkBoxStyleable);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) MyTools.Convert.dpToPx(48));
            checkBoxStyleable.setLayoutParams(params);
            return new ViewHolder(checkBoxStyleable);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mCheckBoxStyleable.setChecked(mCheckListValues[position]);
            holder.mCheckBoxStyleable.setText(mItemText[position]);
        }

        @Override
        public int getItemCount() {
            return mCheckListValues.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBoxStyleable mCheckBoxStyleable;


        ViewHolder(View v) {
            super(v);
            mCheckBoxStyleable = (CheckBoxStyleable) v;

        }
    }

}
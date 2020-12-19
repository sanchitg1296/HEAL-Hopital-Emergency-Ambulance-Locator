package com.example.ambulancefinder.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ambulancefinder.R;

import java.util.ArrayList;

public class CustomGuideAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> idImages;
    private ArrayList<String> textViewList;
    public CustomGuideAdapter(Context context, ArrayList<Integer> listId, ArrayList<String> textViewList) {
        this.context = context;
        this.idImages = listId;
        this.textViewList = textViewList;
    }
    @Override
    public int getCount() {
        return textViewList.size();
    }

    @Override
    public Object getItem(int i) {
        return textViewList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return idImages.get(i);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = view.inflate(context, R.layout.activity_guide_list, null);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.medicalProblemImage);
        TextView textView = (TextView) view.findViewById(R.id.medicalProblemText);

        imageView.setImageResource(idImages.get(i));
        textView.setText(textViewList.get(i));

        return view;
    }
}

package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListNumberAdapter extends BaseAdapter {
    final ArrayList<NumToShow> listNumber;
    ListNumberAdapter(ArrayList<NumToShow> listNum)
    {
        this.listNumber = listNum;
    }

    @Override
    public Object getItem(int position) {
        return listNumber.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listNumber.get(position).num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewProduct;
        if(view == null)
        {
            viewProduct = View.inflate(viewGroup.getContext(),R.layout.activity_main,null);
        } else viewProduct = view;

        NumToShow numToShow = (NumToShow)getItem(i);
        ((TextView)viewProduct.findViewById(R.id.numInList)).setText(String.format("so",numToShow.num));
        return viewProduct;
    }

    @Override
    public int getCount() {
        return listNumber.size();
    }
}

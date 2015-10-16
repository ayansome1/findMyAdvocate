package com.example.this_is_ayan.findmyadvocate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.this_is_ayan.findmyadvocate.Objects.CaseCategory;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewRegularFont;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CaseCategory> {

    private List<CaseCategory> items;

    public CategoryAdapter(Context context, int textViewResourceId, List<CaseCategory> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_category, null);
        }
        CaseCategory o = items.get(position);
        if (o != null)
        {
            MyTextViewRegularFont tt = (MyTextViewRegularFont) v.findViewById(R.id.category);

                tt.setText(o.getCaseCategory());

        }
        return v;
    }
}
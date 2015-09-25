package com.example.this_is_ayan.findmyadvocate.Adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.this_is_ayan.findmyadvocate.Objects.caseObject;
import com.example.this_is_ayan.findmyadvocate.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Edwin on 18/01/2015.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<caseObject> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<caseObject>();
        caseObject nature = new caseObject();
        nature.setCaseTitle("The Great Barrier Reef");
        nature.setCaseDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        mItems.add(nature);

        nature = new caseObject();
        nature.setCaseTitle("Grand Canyon");
        nature.setCaseDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        mItems.add(nature);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        caseObject nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getCaseTitle());
        viewHolder.tvDesNature.setText(nature.getCaseDescription());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNature = (TextView)itemView.findViewById(R.id.title);
            tvDesNature = (TextView)itemView.findViewById(R.id.description);
        }
    }
}
package com.example.this_is_ayan.findmyadvocate.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.this_is_ayan.findmyadvocate.Objects.caseObject;
import com.example.this_is_ayan.findmyadvocate.R;
import java.util.ArrayList;
import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>
{
    LayoutInflater inflater;

    List<caseObject> mItems= new ArrayList<>();
    public CardAdapter(Context context,List<caseObject> mItems)
    {
        inflater = LayoutInflater.from(context);
        this.mItems = mItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        caseObject cases = mItems.get(i);
        viewHolder.caseTitle.setText(cases.getCaseTitle());
        viewHolder.caseDescription.setText(cases.getCaseDescription());
    }

    @Override
    public int getItemCount()
    {
           return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView caseTitle;
        public TextView caseDescription;
        public ViewHolder(View itemView)
        {
            super(itemView);
            caseTitle = (TextView)itemView.findViewById(R.id.title);
            caseDescription = (TextView)itemView.findViewById(R.id.description);
        }
    }
}
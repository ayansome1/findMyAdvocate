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


/**
 * Created by Edwin on 18/01/2015.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>
{
    LayoutInflater inflater;

    List<caseObject> mItems= new ArrayList<>();
    public CardAdapter(Context context,List<caseObject> mItems)
    {
     //   super();
       // mItems = new ArrayList<caseObject>();


        inflater = LayoutInflater.from(context);
        this.mItems = mItems;

    }


  /*  public CardAdapter()
    {
        super();
        mItems = new ArrayList<caseObject>();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("cases");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> caseList, ParseException e) {
                if (e == null)
                {
                    int len = caseList.size();
                    for (int i = 0; i < len; i++)
                    {
                        ParseObject p = caseList.get(i);
                        String caseTitle = p.getString("caseTitle");
                        String caseDescription = p.getString("caseDescription");

                        System.out.println("case description fetched from parse and getitemcount is " + getItemCount());


                        caseObject cases = new caseObject();
                        cases.setCaseTitle(caseTitle);
                        cases.setCaseDescription(caseDescription);
                        mItems.add(cases);



                    }


                }
                else
                {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });




    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

       System.out.println("oncreateviewholder called");



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
      //  if(mItems==null)
        //    return;
       // System.out.println("not yet called");
        caseObject cases = mItems.get(i);

        viewHolder.caseTitle.setText(cases.getCaseTitle());
        viewHolder.caseDescription.setText(cases.getCaseDescription());
        System.out.println("onbindviewholder called");

        System.out.println("case description set and getitemcountis :"+getItemCount());



        //case description set
    }

    @Override
    public int getItemCount()
    {
     //  if(mItems.size()>0)
       //{
           System.out.println("getcount is : " + mItems.size());
           return mItems.size();
       // return 3;

        //}
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView caseTitle;
        public TextView caseDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            caseTitle = (TextView)itemView.findViewById(R.id.title);
            caseDescription = (TextView)itemView.findViewById(R.id.description);
        }
    }
}
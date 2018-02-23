package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses;



/**
 * Created by HP on 16/02/2018.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.DisplaySQLiteDataActivity;
import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.R;
import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.entities.Person;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import android.widget.Filterable;

public class ListAdapter extends BaseAdapter implements Filterable{

    Context context;

    ArrayList<String> ID;
    ArrayList<String> FName;
    ArrayList<String> LName;
    ArrayList<String> Address;
    ArrayList<String> PhoneNumber;

    ////
    private List<Person> OriginalData; //the original data
    private List<Person> FilteredData;//the filtered data
    //private LayoutInflater mLayoutInflater;
    //////

   /////
    public ListAdapter(Context context,List<Person> data) {
        //this.context = context;
        //data.addAll(Person);
        this.OriginalData = data;
        this.FilteredData = data;
        //mLayoutInflater = LayoutInflater.from(context);
    }
  /////

    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> Fname,
            ArrayList<String> Lname,
            ArrayList<String> Adr,
            ArrayList<String> phone
    )
    {

        this.context = context2;
        this.ID = id;
        this.FName = Fname;
        this.LName = Lname;
        this.Address = Adr;
        this.PhoneNumber = phone;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
        //return  FilteredData.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;

    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        //return 0;
        return position;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.activity_items, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.FName_TextView = (TextView) child.findViewById(R.id.textViewFirstNAME);
            holder.LName_TextView = (TextView) child.findViewById(R.id.textViewLastNAME);
            holder.Address_TextView = (TextView) child.findViewById(R.id.textViewAddress);
            holder.PhoneNumberTextView = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.FName_TextView.setText(FName.get(position));
        holder.LName_TextView.setText(LName.get(position));
        holder.Address_TextView.setText(Address.get(position));
        holder.PhoneNumberTextView.setText(PhoneNumber.get(position));

        //
        //holder.FName_TextView.setText(FilteredData.get(position).getFirstName());

        return child;
    }

    /// Filter Method

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = OriginalData.size();
                    results.values = OriginalData;
                } else {//do the search
                    List<String> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString().toUpperCase();
                    for (Person p : OriginalData)
                        if (p.getFirstName().toUpperCase().startsWith(searchStr)) resultsData.add(p.getFirstName());
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                FilteredData = (ArrayList<Person>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    // Filter Method
    public void Filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        /*PersonsList.clear();

        if(charText.length() == 0){
            PersonsList.addAll(PersonsArrayList);
        }else{
            for (String p : PersonsArrayList){
                 if(p.toLowerCase(Locale.getDefault()).contains(charText)){
                    PersonsList.add(p);
                 }else if(p.toLowerCase(Locale.getDefault()).contains(charText)){
                     PersonsList.add(p);
                 }else if (p.toLowerCase(Locale.getDefault()).contains(charText)){
                    PersonsList.add(p);
                 }else{
                    PersonsList.add(null);
                 }
            }
        }
        notifyDataSetChanged(); */
    }


}

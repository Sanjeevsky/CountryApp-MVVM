package com.dev.countryapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.countryapp.R;
import com.dev.countryapp.model.CountryModel;
import com.dev.countryapp.view.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateCountries(List<CountryModel> countries){
        this.countries.clear();
        this.countries.addAll(countries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        if(countries==null) return 0;
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.countryImage)
        ImageView countryImage;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.capital)
        TextView capital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(CountryModel countryModel){
            name.setText(countryModel.getCountryName());
            capital.setText(countryModel.getCapital());
            Util.loadImage(countryImage,countryModel.getFlag(),Util.getProgressDrawable(countryImage.getContext()));
        }
    }
}

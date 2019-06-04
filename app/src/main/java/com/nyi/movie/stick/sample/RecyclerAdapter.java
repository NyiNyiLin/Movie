package com.nyi.movie.stick.sample;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nyi.movie.R;
import com.nyi.movie.stick.StickHeaderRecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends StickHeaderRecyclerView<CustomerData, HeaderDataImpl> {

    ControllerDataViewHolder controllerDataViewHolder;

    private ArrayList<String> headerList;

    public RecyclerAdapter(ArrayList<String> headerList, ControllerDataViewHolder controllerDataViewHolder) {
        this.controllerDataViewHolder = controllerDataViewHolder;
        this.headerList = headerList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HeaderDataImpl.HEADER_TYPE_1:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header1_item_recycler, parent, false));
            case HeaderDataImpl.HEADER_TYPE_2:
                return new Header2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header2_item_recycler, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false), controllerDataViewHolder);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindData(position);
        } else if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).bindData(position);
        } else if (holder instanceof Header2ViewHolder){
            ((Header2ViewHolder) holder).bindData(position);
        }
    }


    @Override
    public void bindHeaderData(View header, int headerPosition) {

        /*TextView tv = header.findViewById(R.id.tvHeader);
        tv.setText(String.valueOf(headerPosition / 5));*/
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        HeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }

        void bindData(int position) {
            tvHeader.setText(headerList.get(position/5));
        }
    }

    class Header2ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        Header2ViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }

        void bindData(int position) {
            Log.i("movieewwww", String.valueOf(position));
            //tvHeader.setText(getDataInPosition(position).getTitle());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRows;
        CustomerData customerData;

        ViewHolder(View itemView, final ControllerDataViewHolder controllerDataViewHolder) {
            super(itemView);
            tvRows = itemView.findViewById(R.id.tvRows);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    controllerDataViewHolder.onClickData(customerData);
                }
            });
        }

        void bindData(int position) {
            this.customerData = getDataInPosition(position);
            tvRows.setText(customerData.getTitle());
            ((ViewGroup) tvRows.getParent()).setBackgroundColor(Color.parseColor("#ffffff"));
        }


    }
    interface ControllerDataViewHolder{
        void onClickData(CustomerData customerData);
    }

}

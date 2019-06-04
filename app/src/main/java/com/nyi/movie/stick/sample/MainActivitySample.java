package com.nyi.movie.stick.sample;

import android.os.Bundle;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nyi.movie.R;
import com.nyi.movie.stick.StickHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivitySample extends AppCompatActivity implements RecyclerAdapter.ControllerDataViewHolder {

    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);


        list.add("Header 1");
        list.add("Header 2");
        list.add("Header 3");
        list.add("Header 4");
        list.add("Header 5");
        list.add("Header 6");
        list.add("Header 7");


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(list, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        setData(adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new StickHeaderItemDecoration(adapter));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setData(RecyclerAdapter adapter) {
        HeaderDataImpl headerData1 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.header1_item_recycler);
        HeaderDataImpl headerData2 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_2, R.layout.header2_item_recycler);

        List<CustomerData> items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData1);


        items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData2);

        items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData1);

        items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData2);

        items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData1);

        items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData2);

        items = new ArrayList<>();
        items.add(new CustomerData("asd"));
        items.add(new CustomerData("def"));
        items.add(new CustomerData("afg"));
        items.add(new CustomerData("agh"));
        adapter.setHeaderAndData(items, headerData1);
    }

    @Override
    public void onClickData(CustomerData customerData) {
        Toast.makeText(this, customerData.getTitle(), Toast.LENGTH_SHORT).show();
    }
}

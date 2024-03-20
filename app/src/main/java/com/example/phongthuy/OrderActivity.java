package com.example.phongthuy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView rvOrder;
    private List<Product> mListProduct;
    private OrderAdapter mOrderAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Product product = getIntent().getParcelableExtra("product");
        initUi();
        getListProductFromDB();
    }
    private Integer calculateTotalPrice(){
        int totalPrice = 0;
        for(Product product:mListProduct){
            Integer price = product.getPrice();
            totalPrice+=price;
        }
        return totalPrice;
    }
    private void getListProductFromDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Order");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                if (product != null) {
                        mListProduct.add(product);
                        mOrderAdapter.notifyItemInserted(mListProduct.size() - 1);
                    }
                    TextView textView_price = findViewById(R.id.textView_Price);
                    textView_price.setText("Tổng tiền: " + calculateTotalPrice() + "$");
                }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private int findProductPositionInList(Product product) {
        for (int i = 0; i < mListProduct.size(); i++) {
            Product item = mListProduct.get(i);
            if (item.getName() != null && product.getName() != null && item.getName().equals(product.getName())) {
                return i;
            }
        }
        return -1;
    }
    private void initUi() {
        rvOrder = findViewById(R.id.rvOrder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvOrder.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvOrder.addItemDecoration(dividerItemDecoration);
        mListProduct = new ArrayList<>();
        mOrderAdapter = new OrderAdapter(mListProduct, new OrderAdapter.IClickListener() {
            @Override
            public void onClickAddItem(Product product) {
                int position = findProductPositionInList(product);
                    mListProduct.add(product);
                    mOrderAdapter.notifyItemInserted(mListProduct.size() - 1);
            }
        });
        rvOrder.setAdapter(mOrderAdapter);
    }
}

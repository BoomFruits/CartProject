package com.example.phongthuy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Product> mListProduct;
    private IClickListener mClickListener;
    public interface  IClickListener {
        void onClickAddItem(Product product);
    }

    public OrderAdapter(List<Product> mListProduct, IClickListener listener) {
        this.mListProduct = mListProduct;
        this.mClickListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if(product == null)
            return;
        holder.textView_name.setText("Tên: " + product.getName());
        holder.textView_descrip.setText("Mô tả: " + product.getDescription());
        holder.textView_price.setText("Giá: " + product.getPrice() + "$");
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_name;
        private TextView textView_descrip;
        private TextView textView_price;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_NAME);
            textView_descrip = itemView.findViewById(R.id.textView_Description);
            textView_price = itemView.findViewById(R.id.textView_Price);
        }
    }
}
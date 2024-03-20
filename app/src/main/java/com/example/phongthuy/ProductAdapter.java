package com.example.phongthuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> lst = new ArrayList<>();
    private Context context;
    private Order order = new Order();
    private int quantity;
    private IClickListener mClickListener;
    public interface  IClickListener {
        void onClickAddItem(Product product);
    }
    public ProductAdapter(List<Product> lst, Context context,IClickListener iClickListener) {
        this.lst = lst;
        this.context = context;
        this.mClickListener = iClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử dish
        View productView = (CardView) inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product =lst.get(position);
        Glide.with(context).load(lst.get(position).getImageUri()).into(holder.img);
        holder.txtName.setText(product.getName());
        holder.txtDescription.setText("Description: "+product.getDescription());
        holder.txtPrice.setText("Price: "+product.getPrice());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClickAddItem(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardview;
        private ImageView img;
        private TextView txtName;
        private TextView txtDescription;
        private TextView txtPrice;
        private ImageView btnAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview);
            img = itemView.findViewById(R.id.item_image_view);
            txtName = itemView.findViewById(R.id.item_name);
            txtDescription = itemView.findViewById(R.id.item_description);
            txtPrice = itemView.findViewById(R.id.item_price);
            btnAdd = itemView.findViewById(R.id.btn_add_to_order);
        }
    }
}

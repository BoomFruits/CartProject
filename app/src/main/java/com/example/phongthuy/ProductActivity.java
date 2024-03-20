package com.example.phongthuy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
public class ProductActivity extends AppCompatActivity {
    List<Product> lstProduct = new ArrayList<>();
    RecyclerView rvProduct;
    ImageView cartBtn;
    //list này lưu trên database nhé
    String[] lstDescription = {"thần tài từ lâu đã trở thành một biểu tượng cho công danh, tài lộc",
            "Tiền vào như nước, tiền ra nhỏ giọt",
            "Ý nghĩa phong thủy của đồng xu ",
            "Trong truyền thuyết cổ, Tỳ hưu là linh vật nổi tiếng có tác dụng chiêu tài, tác lộc, đem lại công danh, sự nghiệp cho gia chủ.",
            "Long Quy là vật bài trí rất được ưa chuộng trong các đình.",
            " Bể cá là một trong số ít vật phong thủy hội tụ 5 yếu tố cơ bản sinh ra vượng khí đem lại may mắn cho gia chủ.",
            "Từ xa xưa, cây cảnh đã được sử dụng để bài trí như một công cụ để mang đến tài lộc cho gia chủ.",
            "Trong thần thoại, thiềm thừ được miêu tả là một chú cóc vàng ba chân, miệng ngậm đồng xu, chân dẫm lên vàng.",
            "Với thân hình to lớn, voi được xem là biểu tượng của sự mạnh mẽ, ổn định về tiền tài trong phong thủy.",
            "Từ xa xưa, rồng đã là biểu tượng riêng của hoàng gia"
    };
    //List này cũng lưu trên database
    String[] lstName = {"Thần tài","Thác nước,chậu nước","Đồng xu may mắn","Tỳ hưu","Long Quy","Bể cá cảnh","Cây cảnh","Thiềm thừ",
            "Voi","Rồng"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);
        initData();
        rvProduct = findViewById(R.id.rvproduct);
        cartBtn = findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductActivity.this,OrderActivity.class);
                startActivity(i);
            }
        });
        ProductAdapter productAdapter = new ProductAdapter(lstProduct, this, new ProductAdapter.IClickListener() {
            @Override
            public void onClickAddItem(Product product) {
                Toast.makeText(ProductActivity.this, "Added", Toast.LENGTH_SHORT).show();
                addProductToOrder(product);
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        rvProduct.setAdapter(productAdapter);
        rvProduct.setLayoutManager(layoutManager);
    }
    private void addProductToOrder(Product product){
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Order").push();
        productRef.setValue(product);
        Toast.makeText(ProductActivity.this, "Thêm bài tập thành công", Toast.LENGTH_SHORT).show();
    }
    private void initData(){
        for (int i = 1; i <=10; i++) {
            Product p = new Product();
            int resID = getResId("ss" + i, R.drawable.class);
            Uri imgUri = getUri(resID);
            p.setImageUri(imgUri);
            p.setPrice(10000+i*i);
            p.setDescription(lstDescription[i-1]);
            p.setName(lstName[i-1]);
            lstProduct.add(p);
        }
    }
    public Uri getUri (int resId){
        return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

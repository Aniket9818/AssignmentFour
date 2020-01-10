package com.e.hamrobazar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.hamrobazar.Model.Product;
import com.e.hamrobazar.R;
import com.e.hamrobazar.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.http.Url;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> productViewList;

    public ProductAdapter(Context context, List<Product> productViewList) {
        this.context = context;
        this.productViewList = productViewList;
    }
    @NonNull
    @Override

    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product productsView=productViewList.get(position);
        holder.textView3.setText(productsView.getName());
        holder.textView2.setText(productsView.getCondition());

        String price=productsView.getPrice();


        holder.textView.setText("Rs. "+price);

        //for setting image in recycle view
        String image=productsView.getImage();
        String imgPath= URL.imagePath+image;

        Picasso.get().load(imgPath).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productViewList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, textView2, textView3;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);

        }
    }
}

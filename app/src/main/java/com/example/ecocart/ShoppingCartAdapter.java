package com.example.ecocart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    List<ShoppingCartItem> cart;
    public ImageView addItemButton;
    Context context;


    public ShoppingCartAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View foodsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);

        return new ViewHolder(foodsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShoppingCartItem item = cart.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodText;

        TextView tvCartItemCurrent;
        TextView tvCartItemAlt;
        TextView tvCartItemCurrentCo2;
        TextView tvCartItemAltCo2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodText = itemView.findViewById(R.id.tvCartItemCurrent);
            tvCartItemCurrent = itemView.findViewById(R.id.tvCartItemCurrent);
            tvCartItemAlt = itemView.findViewById(R.id.tvCartItemAlt);
            tvCartItemCurrentCo2 = itemView.findViewById(R.id.tvCartItemCurrentCo2);
            tvCartItemAltCo2 = itemView.findViewById(R.id.tvCartItemAltCo2);

            addItemButton = itemView.findViewById(R.id.addItemButton);
            addItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = tvFoodText.getText().toString();

                    //database instance of fooddb
                    //query for the item and create food object
                    //insert into shoppingcart table

                }
            });

        }

        public void bind(ShoppingCartItem item) {

            tvCartItemCurrent.setText(item.getName());
            tvCartItemCurrentCo2.setText(Double.toString(item.getCarbonDioxide()));
            //calculate what alternatives to display to ItemAlt and its co2


        }

    }
}
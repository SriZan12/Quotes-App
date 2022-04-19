package com.srijan.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.QuoteViewHolder> {

    List<QuoteList> QuoteList;
    ClickListener clickListener;

    public Adapter(List<com.srijan.quotes.QuoteList> quoteList,ClickListener clickListener) {
        QuoteList = quoteList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_design,parent,false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        holder.QuoteText.setText(QuoteList.get(position).getText());
        holder.Author.setText(QuoteList.get(position).getAuthor());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.CopyText(QuoteList.get(holder.getAdapterPosition()).getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return QuoteList.size();
    }

    public static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView QuoteText,Author;
        CardView cardView;
        Button button;

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);

            QuoteText = itemView.findViewById(R.id.quoteText);
            Author = itemView.findViewById(R.id.author);
            cardView = itemView.findViewById(R.id.cardView);
            button = itemView.findViewById(R.id.copy);
        }
    }
}

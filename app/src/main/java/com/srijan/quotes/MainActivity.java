package com.srijan.quotes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    ClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getList().enqueue(new Callback<List<QuoteList>>() {
            @Override
            public void onResponse(Call<List<QuoteList>> call, @NonNull Response<List<QuoteList>> response) {
                if(response.body().size() > 0){
                    List<QuoteList> quoteLists = response.body();

                    clickListener = new ClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void CopyText(String text) {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", text);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(MainActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        }
                    };
                    Adapter adapter = new Adapter(quoteLists,clickListener);
//                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    recyclerView.setHasFixedSize(true);
//                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<QuoteList>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
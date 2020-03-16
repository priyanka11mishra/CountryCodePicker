package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JSONActivity extends Activity {
    TextView textresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n);
        textresult=(TextView)findViewById(R.id.textviewresult);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Jsonplaceholder json=retrofit.create(Jsonplaceholder.class);
        Call<List<Post>> call= json.getpost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                    textresult.setText("Code:" + response.code());
                    return;
                }

                List<Post> post1=response.body();
                for (Post post:post1) {
                    String content = "";
                    content += "ID:" + post.getId() + "\n";
                    content += "User ID:" + post.getUserid() + "\n";
                    content += "Title:" + post.getTitle() + "\n";
                    content += "Text:" + post.getText() + "\n /n";

                    textresult.append(content);


                }



            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textresult.setText(t.getMessage());

            }
        });
    }
}

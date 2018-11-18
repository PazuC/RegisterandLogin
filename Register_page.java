package com.example.pazu.registerandlogin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register_page extends Fragment {
    private Button register;
    private Button back;
    private EditText userName;
    private EditText password;
    private TextView mText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view_register_page = inflater.inflate(R.layout.fragment_register_page, container, false);
        register = view_register_page.findViewById(R.id.register);
        back = view_register_page.findViewById(R.id.back);
        final EditText userName = view_register_page.findViewById(R.id.userName);
        final EditText password = view_register_page.findViewById(R.id.password);
        mText = view_register_page.findViewById(R.id.mText);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Login_page login_page = new Login_page();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.container, login_page);
                    ft.commit();
                }
            });

             register.setOnClickListener(new View.OnClickListener() {
                  @Override
                 public void onClick(View v) {
                      Gson gson = new Gson();

                      AndroidNetworking.post("https://hinl.app:9990/register")
                              .addJSONObjectBody(gson.toJson(user)) // posting json
                              .setTag("test")
                              .setPriority(Priority.MEDIUM)
                              .build()
                              .getAsJSONArray(new JSONArrayRequestListener() {
                                  @Override
                                  public void onResponse(JSONArray response) {
                                      // do anything with response
                                      mText.setText("Response: " + response.toString());
                                  }
                                  @Override
                                  public void onError(ANError error) {
                                      // handle error
                                  }
                              });
                  }
             });
        return view_register_page;
    }




}

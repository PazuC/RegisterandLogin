package com.example.pazu.registerandlogin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONObject;
import org.json.JSONException;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register_page extends Fragment {
    private Button register;
    private Button back;
    private EditText userName;
    private EditText password;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view_register_page = inflater.inflate(R.layout.fragment_register_page, container, false);
        register = view_register_page.findViewById(R.id.register);
        back = view_register_page.findViewById(R.id.back);
        final EditText userName = view_register_page.findViewById(R.id.userName);
        final EditText password = view_register_page.findViewById(R.id.password);


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

                    AndroidNetworking.post("https://hinl.app:9990/register")
                            .addBodyParameter("userName",  String.valueOf(userName))
                            .addBodyParameter("passwd", String.valueOf(password))
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
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

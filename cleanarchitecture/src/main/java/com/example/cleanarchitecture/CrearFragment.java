package com.example.cleanarchitecture;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrearFragment extends Fragment {

    private EditText eNombre, eTelefono, eCorreo;
    private Button bCrear;

    /*ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;*/

    public CrearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        eTelefono = view.findViewById(R.id.eTelefono);
        eCorreo = view.findViewById(R.id.eCorreo);
        bCrear = view.findViewById(R.id.bCrear);

       /*contactosSQLiteHelper = new ContactosSQLiteHelper(getActivity(),"Agenda",null,1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();*/

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 /*ContentValues dataBD = new ContentValues();
                dataBD.put("nombre", eNombre.getText().toString());
                dataBD.put("telefono", eTelefono.getText().toString());
                dataBD.put("correo", eCorreo.getText().toString());

                dbContactos.insert("contactos", null,dataBD);*/

                class CrearContacto extends AsyncTask<Void, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(getActivity(), "crear contacto", "creando....", false, false);
                    }

                    @Override
                    protected String doInBackground(Void... voids) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("nombre", eNombre.getText().toString());
                        params.put("telefono", eTelefono.getText().toString());
                        params.put("correo", eCorreo.getText().toString());

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Config.URL_CREAR, params);
                        return res;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }
                }

                CrearContacto crear = new CrearContacto();
                crear.execute();
            }
        });

        return view;
    }



}

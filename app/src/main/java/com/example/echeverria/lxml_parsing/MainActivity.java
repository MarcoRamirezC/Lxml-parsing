package com.example.marc.lxml_parsing;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MainActivity extends ListActivity {

    // All static variables
    static final String URL = " http://10.0.2.2/app/bbdd.xml";
    // XML node keys
    static final String KEY_CONTACTO = "contacto"; // parent node
    static final String KEY_ID_A = "id_A";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDOP = "apellido_p";
    static final String KEY_APELLIDOM = "apellido_m";
    static final String KEY_EMAIL = "email";
    static final String KEY_TELEFONO = "telefono";
    static final String KEY_EDAD = "edad";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_CONTACTO);
        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID_A, parser.getValue(e, KEY_ID_A));
            map.put(KEY_NOMBRE, parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_APELLIDOP, parser.getValue(e, KEY_APELLIDOP));
            map.put(KEY_APELLIDOM, parser.getValue(e, KEY_APELLIDOM));
            map.put(KEY_EMAIL, parser.getValue(e, KEY_EMAIL));
            map.put(KEY_TELEFONO, "Tel." + parser.getValue(e, KEY_TELEFONO));
            map.put(KEY_EDAD, parser.getValue(e, KEY_EDAD));

            // adding HashList to ArrayList
            menuItems.add(map);
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.lista_contactos,
                new String[] { KEY_ID_A, KEY_NOMBRE }, new int[] {
                R.id.id, R.id.nombre});

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();
        // listening to single listitem click
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String lblid = ((TextView) view.findViewById(R.id.id)).getText().toString();
                String lblnombre = ((TextView) view.findViewById(R.id.nombre)).getText().toString();


                // Starting new intent
                Intent in = new Intent(getApplicationContext(), vista_individual.class);
                in.putExtra(KEY_ID_A, lblid);
                in.putExtra(KEY_NOMBRE, lblnombre);

                startActivity(in);

            }
        });
    }
}
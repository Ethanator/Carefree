package com.carefree.carefree;

/**
 * Created by Normn on 4/12/2014.
 */
import android.app.ListActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.ListView;
        import android.widget.TextView;

public class ListMaker extends ListActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setupUI();
    }

    public void setupUI()
    {
        ArrayAdapter<String> la = new ArrayAdapter<String>(this, R.layout.list_row);
        la.add("Mercury");
        la.add("Venus");
        la.add("Earth");
        la.add("Mars");
        la.add("Jupiter");
        la.add("Saturn");
        la.add("Uranus");
        la.add("Neptune");
        la.add("Pluto");

        setListAdapter(la);

        ListView lv = getListView();
    }

    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id)
    {
        String planet = ((TextView) view).getText().toString();
        setTitle(planet);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id)
    {
        String planet = ((TextView) view).getText().toString();
        setTitle(planet);
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // not implemented
    }
}
package com.jds.dsd.geograpp.areasGeograficas;


import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.jds.dsd.geograpp.R;

import info.sqlite.helper.AreasContract;

public class FragmentListCompass extends ListFragment {

    private static final String[] FROM = {AreasContract.NAME, AreasContract.DESCRIPTION};
    private static final int[] TO = {R.id.titleArea, R.id.descriptionArea};

    private SimpleCursorAdapter cursorAdapter;
    private AreasContract areasContract;
    private Cursor cursor;
    private int idArea;

    public FragmentListCompass() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        areasContract = new AreasContract(getActivity());
        idArea = getActivity().getIntent().getIntExtra("idArea", -1);
        cursor = areasContract.getCompassMeasureByArea(idArea);
        cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_lista_areas, cursor, FROM, TO, 0);

        setListAdapter(cursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), DetailsCompass.class);
        intent.putExtra("idCompass", id);
        intent.putExtra("idArea", idArea);
        startActivity(intent);
    }
}

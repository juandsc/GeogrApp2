package com.jds.dsd.geograpp.areasGeograficas;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.jds.dsd.geograpp.R;

import com.jds.dsd.geograpp.areasGeograficas.dummy.DummyContent;

import info.sqlite.helper.AreasContract;


public class FragmentListQuality extends ListFragment {
    private static final String[] FROM = {AreasContract.NAME, AreasContract.DESCRIPTION};
    private static final int[] TO = {R.id.titleArea, R.id.descriptionArea};

    private SimpleCursorAdapter cursorAdapter;
    private AreasContract areasContract;
    private Cursor cursor;
    private int idArea;

    public FragmentListQuality() {
    }

    @Override
    public void onResume() {
        super.onResume();
        areasContract = new AreasContract(getActivity());
        idArea = getActivity().getIntent().getIntExtra("idArea", -1);
        cursor = areasContract.getRockQualityByArea(idArea);
        cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_lista_areas, cursor, FROM, TO, 0);

        setListAdapter(cursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(getActivity(), DetailQuality.class);
        intent.putExtra("idQuality", id);
        intent.putExtra("idArea", idArea);
        startActivity(intent);
    }
}

package com.jds.dsd.geograpp.areasGeograficas;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.jds.dsd.geograpp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import info.sqlite.helper.AreasContract;
import info.sqlite.helper.DbHelper;
import info.sqlite.helper.GeograficArea;

public class FragmentListaAreas extends ListFragment{
    private static final String[] FROM = {AreasContract.NAME, AreasContract.DESCRIPTION};
    private static final int[] TO = {R.id.titleArea, R.id.descriptionArea};

    private SimpleCursorAdapter cursorAdapter;
    private AreasContract areasContract;
    private Cursor cursor;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        areasContract = new AreasContract(getActivity());
        cursor = areasContract.getAllGeograficArea();
        cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_lista_areas, cursor, FROM, TO, 0);

        setListAdapter(cursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        startActivity(new Intent(getActivity(), DetailArea.class).putExtra(areasContract.ID, id));
    }


}

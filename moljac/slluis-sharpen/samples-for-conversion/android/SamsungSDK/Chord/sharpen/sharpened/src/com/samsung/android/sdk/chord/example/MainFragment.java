/**
 * Copyright (C) 2013 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Mobile Communication Division,
 * Digital Media & Communications Business, Samsung Electronics Co., Ltd.
 *
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 *
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 */

package com.samsung.android.sdk.chord.example;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainFragment extends ListFragment {
    public interface MainFragmentListener {
        public void setFragment(int choice);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> mMain_adapter = new ArrayAdapter<String>(getActivity()
                .getBaseContext(), R.layout.simple_list_item, menu);
        setListAdapter(mMain_adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.setFragment(position);
        super.onListItemClick(l, v, position, id);
    }

    public void setListener(MainFragmentListener listener) {
        mListener = listener;
    }

    private MainFragmentListener mListener;
    private static final String[] menu = {
            "Hello Chord", "Send Files", "Use Secure Channel", "Udp Framework", "License"
    };
}

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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.samsung.android.sdk.chord.example.MainFragment.MainFragmentListener;

public class ChordSampleAppActivity extends Activity implements MainFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chord_sample_app_activity);

        mVersionInfoLayout = (LinearLayout)findViewById(R.id.version_info_layout);

        mFragmentTransaction = getFragmentManager().beginTransaction();

        MainFragment main = new MainFragment();
        main.setListener(this);       
        mFragmentTransaction.replace(R.id.fragment_container, main);
        mFragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        mVersionInfoLayout.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }

    @Override
    public void setFragment(int choice) {
        if (choice == LICENSEVIEW) {
            showLicenseViewDialog();
            return;
        }

        mVersionInfoLayout.setVisibility(View.GONE);
        mFragmentTransaction = getFragmentManager().beginTransaction();
        switch (choice) {
            case HELLOCHORDFRAGMENT:
                HelloChordFragment helloChordFragment = new HelloChordFragment();
                mFragmentTransaction.replace(R.id.fragment_container, helloChordFragment);
                break;
            case SENDFILESFRAGMENT:
                SendFilesFragment sendFilesFragment = new SendFilesFragment();
                mFragmentTransaction.replace(R.id.fragment_container, sendFilesFragment);
                break;
            case USESECURECHANNEL:
                UseSecureChannelFragment useSecureFragment = new UseSecureChannelFragment();
                mFragmentTransaction.replace(R.id.fragment_container, useSecureFragment);
                break;
            case UDPFRAMEWORK:
                UdpFrameworkFragment udpFrameworkFragment = new UdpFrameworkFragment();
                mFragmentTransaction.replace(R.id.fragment_container, udpFrameworkFragment);
                break;
            default:
                break;
        }
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();

    }


    private void showLicenseViewDialog() {
        String license;
        InputStream is = getResources().openRawResource(R.raw.license);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        try {
            int i = is.read();
            while (i != -1) {
                bs.write(i);
                i = is.read();
            }

        } catch (Exception e) {

        } finally {
            try {
                is.close();
            } catch (Exception e) {

            }
        }

        license = bs.toString();

        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(R.string.license)
                .setMessage(license).create();
        alertDialog.show();
    }

    
    private static final int HELLOCHORDFRAGMENT = 0;

    private static final int SENDFILESFRAGMENT = 1;

    private static final int USESECURECHANNEL = 2;

    private static final int UDPFRAMEWORK = 3;

    private static final int LICENSEVIEW = 4;

    private FragmentTransaction mFragmentTransaction;

    private LinearLayout mVersionInfoLayout;

}

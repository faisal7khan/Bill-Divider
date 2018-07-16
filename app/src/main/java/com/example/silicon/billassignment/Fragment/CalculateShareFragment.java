package com.example.silicon.billassignment.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.silicon.billassignment.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class CalculateShareFragment extends Fragment {
    private Spinner numberOfPersonsDropDown;
    private static final String[]persons = {"1","2","3","4","5","6","7","8","9","10"};
    private SeekBar tipPercentageSeekbar;
    private TextView seekbarPercentage;
    private TextView amountEachPersonPayTxt;
    private EditText tiptxt;
    private TextView totalBillTxt;

    private float totalBill = -1;
    private float amountEachPersonPay = 0;

    public CalculateShareFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculate_share, container, false);

        //txtTotalCount = rootView.findViewById(R.id.textViewTotalCount);
        numberOfPersonsDropDown = (Spinner)rootView.findViewById(R.id.totalPersonsSpinner);
        tipPercentageSeekbar = rootView.findViewById(R.id.fragment_calculate_tipPercentage_seekbar);
        seekbarPercentage = rootView.findViewById(R.id.fragment_calculate_seekpercentage_txt);
        amountEachPersonPayTxt = rootView.findViewById(R.id.fragment_calculate_each_pay_txt);
        tiptxt = rootView.findViewById(R.id.fragment_calculate_tip_txt);
        totalBillTxt = rootView.findViewById(R.id.fragment_calculate_total_bill_txt);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,persons);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfPersonsDropDown.setAdapter(adapter);
        numberOfPersonsDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // txtTotalCount.setText((position+1)+"");
                float tempBill = Float.parseFloat(tiptxt.getText().toString().trim()) + totalBill;
                amountEachPersonPay = tempBill/Integer.parseInt(numberOfPersonsDropDown.getSelectedItem().toString());
                amountEachPersonPayTxt.setText(amountEachPersonPay+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tipPercentageSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!fromUser)
                    return;
                seekbarPercentage.setText(progress+"%");
                tiptxt.setText(((((float)progress/100)*totalBill))+"");
                float tempBill = Float.parseFloat(tiptxt.getText().toString().trim()) + totalBill;
                amountEachPersonPay = tempBill/Integer.parseInt(numberOfPersonsDropDown.getSelectedItem().toString());
                amountEachPersonPayTxt.setText(amountEachPersonPay+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tiptxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float tipTotalPercentage = 0;
                try{
                     tipTotalPercentage = Float.parseFloat(s.toString().trim())/totalBill;
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }


                if (tipTotalPercentage <= 1){
                    tipPercentageSeekbar.setProgress((int)(tipTotalPercentage * 100));
                }
                else{
                    tipPercentageSeekbar.setProgress(100);
                }
                if(tipTotalPercentage <= 0){
                    tipTotalPercentage = 0;
                    tipPercentageSeekbar.setProgress(0);
                }
                seekbarPercentage.setText((tipTotalPercentage * 100)+"%");
               // tiptxt.setText((((progress/100)*totalBill))+"");
                float tempBill = totalBill;
                try{
                     tempBill = Float.parseFloat(s.toString().trim()) + totalBill;
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                amountEachPersonPay = tempBill/Integer.parseInt(numberOfPersonsDropDown.getSelectedItem().toString());
                amountEachPersonPayTxt.setText(amountEachPersonPay+"");
            }
        });

        amountEachPersonPay = totalBill/Integer.parseInt(numberOfPersonsDropDown.getSelectedItem().toString());
        amountEachPersonPayTxt.setText(amountEachPersonPay+"");
        totalBillTxt.setText("Total Bill: "+totalBill);
        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

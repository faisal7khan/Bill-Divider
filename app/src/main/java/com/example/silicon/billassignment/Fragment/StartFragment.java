package com.example.silicon.billassignment.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.silicon.billassignment.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StartFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button btnContinue;
    private EditText totalBillTxt;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        btnContinue = rootView.findViewById(R.id.btnContinue);
        totalBillTxt = rootView.findViewById(R.id.fragment_start_txtTotal);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    float total = Float.parseFloat(totalBillTxt.getText().toString().trim());

                    if( total <= 0){
                        return;
                    }
                    CalculateShareFragment claculateFragment = new CalculateShareFragment();
                    claculateFragment.setTotalBill(total);
                    FragmentTransaction fragmentTransaction =  getActivity().getSupportFragmentManager().beginTransaction();
                    if(fragmentTransaction == null){
                        return;
                    }

                    fragmentTransaction.add(R.id.fragment_container_content_view,claculateFragment);
                    //fragmentTransaction.r
                    fragmentTransaction.commitAllowingStateLoss();
                    fragmentTransaction.remove(StartFragment.this);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

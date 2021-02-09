package com.example.android.fragmentexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimpleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CHOICE = "choice"; // ini untuk Key


    // TODO: Rename and change types of parameters
    /*mRadioButtonChoice == 2 -> belum memilih */
    private int mRadioButtonChoice = 2;


    private OnFragmentInteractionListener mListener;

    public SimpleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SimpleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(int choice){
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        /*mengapa choice int karena aka memasukan isi dari mRadioButtonChoice
        * args.putString -> untuk String
        *
        */
        args.putInt(CHOICE, choice);
        fragment.setArguments(args);
        return fragment;
    }

    /* Membuat fragment */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /* Membuat View Fragment */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /* mengarankan ke layout fragment */
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final TextView textView = rootView.findViewById(R.id.fragment_header);

        /* Jika sudah pernah diisi ( mRadioButtonChoice == 2) maka menampilkan value */
        if (getArguments().containsKey(CHOICE)){
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            if (mRadioButtonChoice != 2 ){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        /* untuk menggunakan radio grup (radio button) */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                /*index sesuai urutan yang berada di layout fragment*/
                switch (index){
                    case 0 :
                        textView.setText("ARTICLE: Like !");
                        mRadioButtonChoice = 0;
                        break;
                    case 1 :
                        textView.setText("ARTICLE: Thanks !");
                        mRadioButtonChoice = 1;
                        break;
                    default :
                        mRadioButtonChoice = 1;
                        break;
                }
                mListener.onRadioButtonChoice(mRadioButtonChoice);
            }
        });

        return rootView;
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
        void onRadioButtonChoice(int mRadioButtonChoice);
    }
}

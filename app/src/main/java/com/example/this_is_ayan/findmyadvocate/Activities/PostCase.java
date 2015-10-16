package com.example.this_is_ayan.findmyadvocate.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.this_is_ayan.findmyadvocate.Adapters.CategoryAdapter;
import com.example.this_is_ayan.findmyadvocate.Objects.CaseCategory;
import com.example.this_is_ayan.findmyadvocate.Objects.cases;
import com.example.this_is_ayan.findmyadvocate.R;
import com.example.this_is_ayan.findmyadvocate.Widgets.ConnectionDetector;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyEditTextLightFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewLightFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.MyTextViewRegularFont;
import com.example.this_is_ayan.findmyadvocate.Widgets.PlaceJSONParser;
import com.example.this_is_ayan.findmyadvocate.Widgets.ProgressView;
import com.example.this_is_ayan.findmyadvocate.Widgets.Switch;
import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostCase extends ActionBarActivity
{

 //   CaseCategory item[];
  //  ArrayAdapter<CaseCategory> adapterCaseCategory;
  //  ArrayAdapter<CaseCategory> adapterCaseCategory;
    CategoryAdapter categoryAdapter;

    SimpleAdapter adapter;

    Toolbar toolbar;
    Switch switchProfileVisibility;
    com.example.this_is_ayan.findmyadvocate.Widgets.EditText titleEditText,descriptionEditText;
    String title,description,location;
    boolean titleFilledBoolean,descriptionFilledBoolean;
    ImageView saveImageView;
    MyTextViewLightFont locationTextView,caseCategoryTextView;

    MyTextViewRegularFont ok,cancel,dialogText,error;
    Dialog dialog,dialogLoading,dialogError;
    Context mContext;
    ProgressView progressView,progressViewLocation;
    KeyListener titleKeyListener,descriptionKeyListener;
    InputMethodManager imm;
    ConnectionDetector cd ;
    Boolean isInternetPresent;

    MyEditTextLightFont atvPlaces;
    ListView l, listViewCaseCategories;
    PlacesTask placesTask;
    ParserTask parserTask;


    //   Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_case);



        locationTextView =(MyTextViewLightFont)findViewById(R.id.location);
        caseCategoryTextView=(MyTextViewLightFont)findViewById(R.id.case_category);

        switchProfileVisibility=(Switch)findViewById(R.id.Switch);
        progressView =(ProgressView)findViewById(R.id.progress_view);

       cd = new ConnectionDetector(getApplicationContext());

        mContext=this;

        titleFilledBoolean=descriptionFilledBoolean=false;
        switchProfileVisibility=(Switch)findViewById(R.id.Switch);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        saveImageView=(ImageView)findViewById(R.id.save);


        caseCategoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setContentView(R.layout.case_category);
                dialog.getWindow().getAttributes();
                dialog.show();

                listViewCaseCategories = (ListView)dialog.findViewById(R.id.list_categories);
                //getData();

                categoryAdapter = new CategoryAdapter(getApplicationContext(), R.layout.case_category,getData());

                //Set the above adapter as the adapter of choice for our list
                listViewCaseCategories.setAdapter(categoryAdapter);

                //adapterCaseCategory = new ArrayAdapter<CaseCategory>(getApplicationContext(),android.R.layout.simple_list_item_1, getData());
               //listViewCaseCategories.setAdapter(adapterCaseCategory);






            }
        });



        locationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setContentView(R.layout.dialog_location);

                // WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();

//                wlp.gravity = Gravity.TOP;

                dialog.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
              //  View.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


                dialog.show();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


                progressViewLocation = (ProgressView) dialog.findViewById(R.id.progress_view);


                atvPlaces = (MyEditTextLightFont) dialog.findViewById(R.id.atv_places);
                //  imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.showSoftInput(atvPlaces, InputMethodManager.SHOW_IMPLICIT);

                l = (ListView) dialog.findViewById(R.id.list_cities);


                l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                        HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
                        location = (String) obj.get("description");

                       // dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                        dialog.cancel();
                        locationTextView.setText(location);

                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



                    }
                });


                atvPlaces.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (atvPlaces.getText().length() > 0)
                        {

                            isInternetPresent = cd.isConnectingToInternet();
                            if(isInternetPresent==false)
                            {
                                dialogError = new Dialog(mContext);
                                dialogError.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialogError.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                dialogError.setContentView(R.layout.dialog_error);
                                dialogError.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                                error=(MyTextViewRegularFont)dialogError.findViewById(R.id.error);
                                error.setText("Please check your Internet Connectivity");

                                dialogError.show();
                                ok = (MyTextViewRegularFont) dialogError.findViewById(R.id.ok);
                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogError.cancel();
                                        dialog.cancel();
                                    }
                                });


                                return;


                            }




                            atvPlaces.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_search, 0, R.drawable.ic_navigation_cancel, 0);

                            placesTask = new PlacesTask();
                            placesTask.execute(s.toString());
                            progressViewLocation.start();

                            atvPlaces.setOnTouchListener(new View.OnTouchListener() {

                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    atvPlaces.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_search, 0, R.drawable.ic_navigation_cancel, 0);
                                    final int DRAWABLE_RIGHT = 2;
                                    if (event.getAction() == MotionEvent.ACTION_UP) {
                                        if (event.getRawX() >= (atvPlaces.getRight() - atvPlaces.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                            atvPlaces.setText("");
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                            });


                        } else {
                            l.setAdapter(null);
                            atvPlaces.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_search, 0, 0, 0);

                        }

                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // TODO Auto-generated method stub
                    }
                });


            }
        });

        switchProfileVisibility.setTag("TAG");

       switchProfileVisibility.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(Switch view, boolean checked) {
               if (switchProfileVisibility.isChecked() == true && switchProfileVisibility.getTag() == null) {
                   switchProfileVisibility.setTag("TAG");
                   dialog = new Dialog(mContext);
                   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                   dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                   dialog.setContentView(R.layout.dialog_switch);
                   dialog.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                   dialog.show();
                   dialog.setCanceledOnTouchOutside(false);
                   dialog.setCancelable(false);
                   dialogText = (MyTextViewRegularFont) dialog.findViewById(R.id.switch_text);

                   dialogText.setText("Your profile details will be visible to advocates.Continue?");


                   ok = (MyTextViewRegularFont) dialog.findViewById(R.id.ok);
                   ok.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.cancel();

                       }
                   });

                   cancel = (MyTextViewRegularFont) dialog.findViewById(R.id.cancel);
                   cancel.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           switchProfileVisibility.setChecked(false);
                           dialog.cancel();

                       }
                   });


               } else if (switchProfileVisibility.isChecked() == false && switchProfileVisibility.getTag() == null) {
                   switchProfileVisibility.setTag("TAG");
                   dialog = new Dialog(mContext);
                   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                   dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                   dialog.setContentView(R.layout.dialog_switch);
                   dialog.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                   dialog.show();
                   dialog.setCanceledOnTouchOutside(false);
                   dialog.setCancelable(false);
                   dialogText = (MyTextViewRegularFont) dialog.findViewById(R.id.switch_text);

                   dialogText.setText("Your profile details will not be visible to advocates.Continue?");


                   ok = (MyTextViewRegularFont) dialog.findViewById(R.id.ok);
                   ok.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.cancel();

                       }
                   });

                   cancel = (MyTextViewRegularFont) dialog.findViewById(R.id.cancel);
                   cancel.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           switchProfileVisibility.setChecked(true);
                           dialog.cancel();

                       }
                   });


               }

           }
       });

       switchProfileVisibility.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               switchProfileVisibility.setTag(null);

               return false;
           }
       });




        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        getSupportActionBar().setTitle("");
        titleEditText=(com.example.this_is_ayan.findmyadvocate.Widgets.EditText)findViewById(R.id.title);
        descriptionEditText=(com.example.this_is_ayan.findmyadvocate.Widgets.EditText)findViewById(R.id.description);

        titleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                title=titleEditText.getText().toString();
                if(title.length() != 0)
                {
                    titleFilledBoolean=true;
                    if(descriptionFilledBoolean==true)
                         saveImageView.setVisibility(View.VISIBLE);
                }
                else
                {
                    titleFilledBoolean=false;
                    saveImageView.setVisibility(View.INVISIBLE);

                }
            }
        });


        descriptionEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                description = descriptionEditText.getText().toString();
                if (description.length() != 0) {
                    descriptionFilledBoolean = true;
                    if (titleFilledBoolean == true)
                        saveImageView.setVisibility(View.VISIBLE);
                } else {
                    descriptionFilledBoolean = false;
                    saveImageView.setVisibility(View.INVISIBLE);

                }
            }
        });


        saveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check internet connectivity start
                isInternetPresent = cd.isConnectingToInternet();
                if(isInternetPresent==false)
                {
                    dialogError = new Dialog(mContext);
                    dialogError.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogError.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialogError.setContentView(R.layout.dialog_error);
                    dialogError.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                    error=(MyTextViewRegularFont)dialogError.findViewById(R.id.error);
                    error.setText("Please check your Internet Connectivity");

                    dialogError.show();
                    ok = (MyTextViewRegularFont) dialogError.findViewById(R.id.ok);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogError.cancel();
                        }
                    });


                    return;


                }
                //check internet connectivity end


                saveImageView.setVisibility(View.INVISIBLE);
              //  progressView.start();

                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);



                dialogLoading = new Dialog(mContext);
                dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogLoading.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialogLoading.setContentView(R.layout.dialog_loading);
                dialogLoading.getWindow().getAttributes();
                dialogLoading.show();
                dialogLoading.setCancelable(false);





            /*    titleKeyListener = titleEditText.getKeyListener();
                descriptionKeyListener=descriptionEditText.getKeyListener();*/
              //  titleEditText.setKeyListener(null);
                //descriptionEditText.setKeyListener(null);




              /*  ParseObject cases = new ParseObject("cases");
                cases.put("caseTitle", title);
                cases.put("caseDescription", description);*/
                ParseACL acl = new ParseACL(ParseUser.getCurrentUser());
                acl.setPublicReadAccess(true);

                cases cases = new cases();
                cases.setACL(acl);

                cases.setUser(ParseUser.getCurrentUser());
                cases.put("caseTitle", title);
                cases.put("caseDescription", description);
                //    cases.saveInBackground();


                cases.saveInBackground(new SaveCallback() {

                    public void done(ParseException e) {
                        if (e == null) {
                            dialogLoading.cancel();
                           // progressView.stop();
                            //   saveImageView.setVisibility(View.VISIBLE);
                            // titleEditText.setKeyListener(titleKeyListener);
                            //descriptionEditText.setKeyListener(descriptionKeyListener);

                            dialog = new Dialog(mContext);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setContentView(R.layout.dialog_data_posted);
                            dialog.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.setCancelable(false);

                            ok = (MyTextViewRegularFont) dialog.findViewById(R.id.ok);
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    setResult(2, intent);  // 2 means  refresh needed of the recycler view
                                    finish();

                                }
                            });


                            // saveImageView.setVisibility(View.VISIBLE);


                        }
                        else
                        {
                            saveImageView.setVisibility(View.VISIBLE);
                           // progressView.stop();
                            dialogLoading.cancel();
                            dialogError = new Dialog(mContext);
                            dialogError.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialogError.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialogError.setContentView(R.layout.dialog_error);
                            dialogError.getWindow().getAttributes();//.windowAnimations = R.style.DialogAnimation;
                            dialogError.show();
                            ok = (MyTextViewRegularFont) dialogError.findViewById(R.id.ok);
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogError.cancel();
                                }
                            });

                            //myOb+jectSaveDidNotSucceed();
                        }
                    }
                });


            }
        });
        switchProfileVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });



    }
    @Override
    public void onBackPressed()
    {

        Intent intent=new Intent();
        setResult(1,intent);  // 1 means no refresh needed of the recycler view
        finish();
    }




    /**
     * A method to download json data from url
     */
    public String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception downloading", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches all places from GooglePlaces AutoComplete Web Service
    public class PlacesTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";

            // Obtain browser key from https://code.google.com/apis/console
            String key = "key=AIzaSyDjS69D1LHuh99uEN0vYYy3i0e1fhl6OpI";

            String input = "";

            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            // place type to be searched
            // String types = "types=geocode";

            // place type to be searched
            String types = "types=(cities)&components=country:IN";


            // Sensor enabled
            //String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input + "&" + types + "&"  + key;

            // Output format
            String output = "json";

            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/" + output + "?" + parameters;

            try {
                // Fetching the data from we service
                data = downloadUrl(url);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTask = new ParserTask();

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        @Override
        public List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        @Override
        public void onPostExecute(List<HashMap<String, String>> result) {

            String[] from = new String[]{"description"};
            int[] to = new int[]{android.R.id.text1};

            // Creating a SimpleAdapter for the AutoCompleteTextView
             adapter = new SimpleAdapter(getBaseContext(), result, R.layout.list_item, from, to);

            // Setting the adapter
            //  atvPlaces.setAdapter(adapter);
            l.setAdapter(adapter);
            // l.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressViewLocation.stop();


        }
    }



    public  List<CaseCategory> getData()
    {
        //progressView.start();
        final List<CaseCategory> mItems = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CaseCategories");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> caseList, ParseException e) {

                if (e == null) {
                    int len = caseList.size();
                    for (int i = 0; i <len; i++) {
                        ParseObject p = caseList.get(i);
                        String caseCategory = p.getString("Categories");
                        //String caseDescription = p.getString("caseDescription");
                      //  System.out.println("***"+caseCategory);

                        CaseCategory cases = new CaseCategory();
                        cases.setCaseCategory(caseCategory);
                        //cases.setCaseTitle(caseTitle);
                        //cases.setCaseDescription(caseDescription);
                        mItems.add(cases);
                    }

                      categoryAdapter.notifyDataSetChanged();
                    listViewCaseCategories.setAdapter(categoryAdapter);
                 //   mRecyclerView.setAdapter(mAdapter);
                   // progressView.stop();



                    //mSwipeRefreshLayout.setRefreshing(false);

                }

            }
        });

        return mItems;
    }




}

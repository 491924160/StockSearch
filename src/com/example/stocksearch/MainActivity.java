package com.example.stocksearch;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.animation.AnimatorSet.Builder;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends ActionBarActivity {
	    AutoCompleteTextView symbol;
	    String sym;
	    Button button, newsbtn, facebookbtn;
	    public String URL = "http://cs-server.usc.edu:38054/examples/servlet/finance?symbol=";
	    public String URL1 = "http://chart.finance.yahoo.com/t?s=";
	    public String URL2 = "&lang=enUS&amp;width=300&height=180";
	    
	    
	    public void InitAll(){
	        newsbtn.setVisibility(0);
	        newsbtn.setEnabled(false);
	        facebookbtn.setVisibility(0);
	        facebookbtn.setEnabled(false);
	    }
	    
	    
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        button = (Button)this.findViewById(R.id.button1);
	        newsbtn = (Button)this.findViewById(R.id.button2);
	        facebookbtn = (Button)this.findViewById(R.id.button3);
	       
	             
	        button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AutoCompleteTextView text = (AutoCompleteTextView) MainActivity.this.findViewById(R.id.com);
					final String path = text.getText().toString();	   
					System.out.println(path);
					new FetchInfo().execute(path);
				}
			});
	    }
	    
	    public void searchInfo(View view){
	    	AutoCompleteTextView symbol = (AutoCompleteTextView)this.findViewById(R.id.editText1);
	    	sym = symbol.getText().toString();
	    	
	    	android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage("Please input a symbol");
	    	builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
	    	
	    	AlertDialog dialog = builder.create();
	    	System.out.println(sym);
	    	if(sym.equals("")){
	    		dialog.show();
	    	}
	    	else{
	    		FetchInfo info = new FetchInfo();
	    		info.execute(sym);
	    	}
	    }
	    	
	    class FetchInfo extends AsyncTask<String,Integer,String>{
	    		private Drawable stock;
	    		
	    		public String URL = "http://cs-server.usc.edu:38054/examples/servlet/finance?symbol=";
	    	    public String URL1 = "http://chart.finance.yahoo.com/t?s=";
	    	    public String URL2 = "&lang=enUS&amp;width=300&height=180";
	    	
	    		private Drawable GetImage(String url) throws IOException{
	    			URL image_path = null;
	    			InputStream stream = null;
	    			try{
	    			image_path = new URL(url);
	    			stream = image_path.openStream();
	    			Drawable d = Drawable.createFromStream(stream, "stock_pic");
	    			return d;
	    			}catch (IOException e){
	    				e.printStackTrace();
	    				return null;
	    			}finally{
	    				if(stream != null)
	    					stream.close();
	    			}
	    		}
	    		
	    	protected String doInBackground(String... params) {
	    		String path = URL + params[0];
	    		StringBuilder builder = new StringBuilder();
	    		String path_pic = URL1 + params[0] + URL2;
	    		try {
					InputStream input = new URL(path).openStream();
					BufferedReader json_reader = new BufferedReader(new InputStreamReader(input));
					String info = null;
					while((info=json_reader.readLine())!=null){
						builder.append(info);
					}
					input.close();
					json_reader.close();
					stock = GetImage(path_pic);
					return builder.toString();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}	
	    	}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(result != null){
				try {
					JSONObject json = new JSONObject(result);
					System.out.println(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}else{
					
				}
			}
	    }
	     
	    


//	    }


//	     private class DownloadAutoTask extends AsyncTask <String, Void, String> {
//	        @Override
//	        protected String doInBackground(String... urls) {
//	              
//	            // params comes from the execute() call: params[0] is the url.
//	            try {
//	                return downloadUrl(urls[0]);
//	            } catch (IOException e) {
//	                return "Unable to retrieve web page. URL may be invalid.";
//	            }
//	        }
//	        public void execute(String url) {
//				// TODO Auto-generated method stub
//				
//			}
//			// onPostExecute displays the results of the AsyncTask.
//	        @Override
//	        protected void onPostExecute(String result) {
//	            textView.setText(result);
//	       }
//	    }
//	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}




import java.net.http.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;




class command {
	
	public String path = "";
	
	
	
	public String call( String urlval  ) throws IOException {
		URL url = new URL(path+urlval);
		InputStream is = url.openStream();
		int ptr = 0;
		StringBuffer buffer = new StringBuffer();
		while ((ptr = is.read()) != -1) {
		    buffer.append((char)ptr);
		}
		String output = buffer.toString();
		output =output.trim();
		return output;		
	}

}


public class myclass implements ActionListener{
	
	JFrame  fram = new JFrame();
	JPanel pannel = new JPanel();
	static command myurl = new command();
	
	static command stock = new command();
	JLabel lable;
	

	public myclass() {
		JButton button= new JButton("click me");
		lable = new JLabel("Click button to update");
		
		
		pannel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30) );
		
		pannel.setLayout(new GridLayout( 0 ,1 ) );
		
		pannel.add(lable);
		pannel.add(button);
		button.addActionListener(this);
		fram.add(pannel,BorderLayout.CENTER);
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setTitle("Java Worker");
		fram.pack();
		fram.setVisible(true);
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		myclass letsgp = new myclass();
		
		myurl.path = "http://alexhaussmann.com/adhaussmann/a_final/";
		stock.path = "http://127.0.0.1:8000/doit?";
		System.out.println("Hello world!");
	
		
		
		
	}
	// this is not good code
	
	public String getmainarg(String myvalue) {
		String output = "";
		String comparitor = "(???)";
		char holder[] = new char[comparitor.length()];
		for (int i = 0; i < comparitor.length(); i++) {
			holder[i]='a';
		}
		int counter =0;
		boolean haspassed = false; 
	    for(int i = 0; i<myvalue.length(); i++) {

	        char myval = myvalue.charAt(i);
			for (int m = 1; m < comparitor.length(); m++) {
				holder[m-1]=holder[m];
				
			}
			holder[comparitor.length()-1]= myval;
	        if (haspassed) {
	        	if (counter==comparitor.length()) {
	        		output=output+holder[0];
	        	}
	        	else {
	        		counter=counter+1;
	        	}
	        }
	        Boolean outtruth= true;
	        for (int m = 1; m < 5; m++) {
	        	outtruth=outtruth && holder[m]==comparitor.charAt(m-1);
	        	
	        }
	        
	        if ( outtruth ) {
	        	if (haspassed==false) {
	        		haspassed=true;
	        	}
	        	else {
	        		return output;
	        	}
	        }
	      }
		
		return "NULL";
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		String com1= "NONE";
		String com2= "NONE";
		String[] com2s = null;
		String com2out= "NONE";
		try {
			com2 =  myurl.call("get_post_dev.php?key=d79ab9711bee2a2463770f3fc6aa0f74bc522ea1988efd97ded0185e6f204683&type=all");					
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}
		System.out.println("input = "+com2);
		//System.out.println(com1);
		//System.out.println(com2);
		
		String[] arrOfStr = getmainarg( com2 ).split(",");
		//System.out.println( arrOfStr[0] );
		
		float[] valus  = new float[ arrOfStr.length ];
		
		valus[0]=0;
		for (int i = 1; i < arrOfStr.length; i++) {
			valus[i]=0;
		    try{
		    	valus[i]=Float.parseFloat(arrOfStr[i]);		        
		    }catch(NumberFormatException e1){
		    }
		}
		
		String com3 = "money1_money2,0.75</br>money2_money1,3.5";
		
		try {
			com3 =  stock.call("user=&action_type=print_convertion");					
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}
		
		com3 =com3.replaceAll("</br>",",");
		//System.out.println( com3  );
		String[] backarray = com3.split(",");
		String replace_value = "0";
		
		
		String[] money = getmainarg( com3 ).split(",");
		//System.out.println( backarray.length  );
		for (int i = 1; i < backarray.length; i++) {
			//System.out.println( backarray[i]  );
			
			if(backarray[i].equals("money2_money1") ) {
				

				
			    try{
					replace_value=String.valueOf( Float.parseFloat(backarray[i+1]) );
			    			        
			    }catch(NumberFormatException e1){
			    }
			}
			
		}
		
		String outsting = "";
		for (int i = 1; i < valus.length; i++) {	
			
			outsting+=String.valueOf( valus[i] )+",";
			
			
			
		}
		outsting+=replace_value;
		
		
		
		
		System.out.println("output = "+outsting);
		
		try {
			com1=  myurl.call("change_post.php?uname=Myjavaworker&hashword=Myjavapassword&change_text="+outsting+"&post=d79ab9711bee2a2463770f3fc6aa0f74bc522ea1988efd97ded0185e6f204683");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}
		
		System.out.println(com1);
		
		try {
			com2 =  myurl.call("get_post_dev.php?key=d79ab9711bee2a2463770f3fc6aa0f74bc522ea1988efd97ded0185e6f204683&type=all");					
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}
		
		System.out.println( getmainarg( com2 ) );
		
		lable.setText( "now "+outsting);

		
		//System.out.println( com2 );
		
		
		
		
		
		
	}
	
	

}

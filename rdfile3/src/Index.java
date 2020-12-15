

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import rdfile3.MyModel;


/**
 * Servlet implementation class Index
 */
@WebServlet("")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.setAttribute("total",0);
        request.getRequestDispatcher("template/index.jsp").forward(request, response);
	    }
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	    
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	    Part part = request.getPart("file");
	    BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
		List<MyModel> x= reader(br);
		if(isCSV(part.getHeader("content-disposition"))) {
			String newDate = getDateFile(part.getHeader("content-disposition"));
			createFile(x,newDate);
			br = new BufferedReader(new InputStreamReader(part.getInputStream()));
			request.setAttribute("total", getTotal(br));
			request.setAttribute("file", x);
			request.setAttribute("date", JJMMAAAAToString(newDate));
			
		}else {
			request.setAttribute("total", 0);
			request.setAttribute("erreur", "File Undifined");
		}
    	request.getRequestDispatcher("template/index.jsp").forward(request, response);
	}
	
	
/////////////////FONCTION POUR TROUVER LE NOM DE LA FICHIER D'input..////////////////////////////////////////////////
	public String getDateFile(String contenue) {
		//System.out.println(contenue);
		String name = contenue.substring((contenue.lastIndexOf("\\")+1), contenue.length()).replace(".csv\"", "");
		return name.split("_")[1];
	}
	public boolean isCSV(String contenue) {
		String name = contenue.substring((contenue.lastIndexOf(".")+1), contenue.length()).replace("\"","");
		
		if(name.equals("csv")) {
			return true;
		}
		return false;
	}
	
	
///////////////////////function pour convertir le Montant de DT à Millimes..///////////////////////////
	    public static String convrt1 (double x){
	        x=x*1000;
	        int a = (int)x ;
	        String str=Integer.toString(a);
	        while(str.length()!=20){
	            str='0'+str;
	        }
	        return str;
	    }

//////////function pour convertir les Nbre_Tranx_PaiementFacture et Nbre_Tranx_Recharge de MOBILE et GAB..
	    public static String convrt2 (int x){
	        String str=Integer.toString(x);
	        while(str.length()!=4){
	            str='0'+str;
	        }
	        return str;
	    }
	    
	    
////////////////////FUNCTION POUR REMPLIR LES VARIABLES DU FICHIER///////////////////////////////////
	    public List<MyModel> reader(BufferedReader file){
	    	List<MyModel> x = new ArrayList<>();
	        String line;
	        boolean first=true;
	        double MOBIDINAR_POSTE_TTCASH=0;
	        int nmbr_dab=0;
	        double TELCOMONEY_SMT_TTCASH = 0;
	        int nmbr_poste=0;
	        double MPAYMENT_ATB_TTCASH = 0;
	        int nmbr_atb=0;
	        double MPAYMENT_STB_TTCASH = 0;
	        int nmbr_bh=0;
	        double MPAYMENT_BHB_TTCASH = 0;
	        int nmbr_stb=0;
	        double DAB_ATB_TTCASH = 0;
	        int nmbr_smt=0;
	        //FACTURE
	         double TELCOMONEY_SMT_PFACT = 0;
	         int nmbr_smtf=0;
	         double MPAYMENT_ATB_PFACT = 0;
	         int nmbr_atbf=0;
	         double MPAYMENT_STB_PFACT = 0;
	         int nmbr_stbf=0;
	         double MPAYMENT_BHB_PFACT = 0;
	         int nmbr_bhf=0;

	        try {
				while((line = file.readLine()) != null){
				String[] values = line.split("\\|");
      
      if(first){
				first=false;
      }else{
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[2].equals("21692885417")){
				   double i=Double.parseDouble(values[16]);
				   MOBIDINAR_POSTE_TTCASH = MOBIDINAR_POSTE_TTCASH+i;
				   nmbr_poste++;
         }
         
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[2].equals("21619111222")){
				   double i=Double.parseDouble(values[16]);
				   TELCOMONEY_SMT_TTCASH=TELCOMONEY_SMT_TTCASH+i;
				   nmbr_smt++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[4].equals("14")
         & values[3].equals("152") & (values[8].equals("8")||values[8].equals("9")||values[8].equals("10")||values[8].equals("11")||values[8].equals("12")||values[8].equals("31")) ){
				   double i=Double.parseDouble(values[16]);
				   MPAYMENT_ATB_TTCASH=MPAYMENT_ATB_TTCASH+i;
				   nmbr_atb++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[4].equals("14")
         & values[3].equals("162") & (values[8].equals("8")||values[8].equals("9")||values[8].equals("10")||values[8].equals("11")||values[8].equals("12")||values[8].equals("31")) ){
				   double i=Double.parseDouble(values[16]);
				   MPAYMENT_STB_TTCASH=MPAYMENT_STB_TTCASH+i;
				   nmbr_stb++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[4].equals("14")
         & values[3].equals("156") & (values[8].equals("8")||values[8].equals("9")||values[8].equals("10")||values[8].equals("11")||values[8].equals("12")||values[8].equals("31")) ){
				   double i=Double.parseDouble(values[16]);
				   MPAYMENT_BHB_TTCASH=MPAYMENT_BHB_TTCASH+i;
				   nmbr_bh++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[2].equals("21694847569")){
				   double i=Double.parseDouble(values[16]);
				   DAB_ATB_TTCASH=DAB_ATB_TTCASH+i;
				   nmbr_dab++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))&(values[8].equals("39"))& values[2].equals("21619777888")){
				   double i=Double.parseDouble(values[16]);
				   TELCOMONEY_SMT_PFACT=TELCOMONEY_SMT_PFACT+i;
				   nmbr_smtf++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[4].equals("14")
         & values[3].equals("152") & (values[8].equals("35"))){
				   double i=Double.parseDouble(values[16]);
				   MPAYMENT_ATB_PFACT=MPAYMENT_ATB_PFACT+i;
				   nmbr_atbf++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[4].equals("14")
         & values[3].equals("162") & (values[8].equals("35"))){
				   double i=Double.parseDouble(values[16]);
				   MPAYMENT_STB_PFACT=MPAYMENT_STB_PFACT+i;
				   nmbr_stbf++;
         }
         if((values[29].equals("PST")||values[29].equals("PST1")||values[29].equals("PST2")||values[29].equals("PST7")||values[29].equals("PST8")||values[29].equals("PST9"))& values[4].equals("14")
         & values[3].equals("156") & (values[8].equals("35"))){
				   double i=Double.parseDouble(values[16]);
				   MPAYMENT_BHB_PFACT=MPAYMENT_BHB_PFACT+i;
				   nmbr_bhf++;
         }
      }
      MOBIDINAR_POSTE_TTCASH=Math.round(DAB_ATB_TTCASH *1000)/1000.0;
      TELCOMONEY_SMT_TTCASH=Math.round(TELCOMONEY_SMT_TTCASH *1000)/1000.0;
      MPAYMENT_ATB_TTCASH=Math.round(MPAYMENT_ATB_TTCASH *1000)/1000.0;
      MPAYMENT_STB_TTCASH=Math.round(MPAYMENT_STB_TTCASH *1000)/1000.0;
      MPAYMENT_BHB_TTCASH=Math.round(MPAYMENT_BHB_TTCASH *1000)/1000.0;
      DAB_ATB_TTCASH=Math.round(DAB_ATB_TTCASH *1000)/1000.0;
      TELCOMONEY_SMT_PFACT=Math.round(TELCOMONEY_SMT_PFACT * 1000.0)/1000.0;
      MPAYMENT_ATB_PFACT=Math.round(MPAYMENT_ATB_PFACT *1000)/1000.0;
      MPAYMENT_STB_PFACT=Math.round(MPAYMENT_STB_PFACT *1000)/1000.0;
      MPAYMENT_BHB_PFACT=Math.round(MPAYMENT_BHB_PFACT *1000)/1000.0;
    }
     System.out.print("\nMOBIDINAR_POSTE_TTCASH\t"+"MOBIDINAR/POSTE\t"+"nombre de comptes="+nmbr_poste+" toutale="+MOBIDINAR_POSTE_TTCASH+"\n");
     System.out.print("\nTELCOMONEY_SMT_TTCASH\t"+"TELCOMONEY/SMT\t"+"nombre de comptes="+nmbr_smt+" toutale="+TELCOMONEY_SMT_TTCASH+"\n");
     System.out.print("\nMPAYMENT_ATB_TTCASH\t"+"MobilePayment_ATB\t"+"nombre de comptes="+nmbr_atb+" toutale="+MPAYMENT_ATB_TTCASH+"\n");
     System.out.print("\nMPAYMENT_STB_TTCASH\t"+"MobilePayment_STB\t"+"nombre de comptes="+nmbr_stb+" toutale="+MPAYMENT_STB_TTCASH+"\n");
     System.out.print("\nMPAYMENT_BHB_TTCASH\t"+"MobilePayment_BH\t"+"nombre de comptes="+nmbr_bh+" toutale="+MPAYMENT_BHB_TTCASH+"\n");
     System.out.print("\nDAB_ATB_TTCASH\t"+"DAB_ATB\t"+"nombre de comptes="+nmbr_dab+" toutale="+DAB_ATB_TTCASH+"\n"); 
     ////
     System.out.print("\nTELCOMONEY_SMT_ PFACT\t"+"TELCOMONEY/SMT\t"+"nombre de comptes="+nmbr_smtf+" toutale="+TELCOMONEY_SMT_PFACT+"\n");
     System.out.print("\nMPAYMENT_ATB_ PFACT\t"+"MobilePayment_ATB\t"+"nombre de comptes="+nmbr_atbf+" toutale="+MPAYMENT_ATB_PFACT+"\n");
     System.out.print("\nMPAYMENT_STB_ PFACT\t"+"MobilePayment_STB\t"+"nombre de comptes="+nmbr_stbf+" toutale="+MPAYMENT_STB_PFACT+"\n");

     MyModel mdl= new MyModel();
     mdl.setName("ATB");
     mdl.setPfMontant(MPAYMENT_ATB_PFACT);
     mdl.setPfNombre(nmbr_atbf);
     mdl.setrDABMontant(DAB_ATB_TTCASH);
     mdl.setrDABNombre(nmbr_dab);
     mdl.setRmMontant(MPAYMENT_ATB_TTCASH);
     mdl.setRmNombre(nmbr_atb);
     x.add(mdl);
     
     mdl= new MyModel();
     mdl.setName("STB");
     mdl.setPfMontant(MPAYMENT_STB_PFACT);
     mdl.setPfNombre(nmbr_stbf);
     mdl.setrDABMontant(DAB_ATB_TTCASH);
     mdl.setrDABNombre(nmbr_dab);
     mdl.setRmMontant(MPAYMENT_STB_TTCASH);
     mdl.setRmNombre(nmbr_stb);
     x.add(mdl);
     
     mdl= new MyModel();
     mdl.setName("SMT");
     mdl.setPfMontant(TELCOMONEY_SMT_PFACT);
     mdl.setPfNombre(nmbr_smtf);
     mdl.setrDABMontant(0);
     mdl.setrDABNombre(0);
     mdl.setRmMontant(TELCOMONEY_SMT_TTCASH);
     mdl.setRmNombre(nmbr_smt);
     x.add(mdl);
     
     mdl= new MyModel();
     mdl.setName("BH");
     mdl.setPfMontant(MPAYMENT_BHB_PFACT);
     mdl.setPfNombre(nmbr_bhf);
     mdl.setrDABMontant(DAB_ATB_TTCASH);
     mdl.setrDABNombre(nmbr_dab);
     mdl.setRmMontant(MPAYMENT_BHB_TTCASH);
     mdl.setRmNombre(nmbr_bh);
     x.add(mdl);
     
     mdl= new MyModel();
     mdl.setName("POSTE");
     mdl.setPfMontant(0);
     mdl.setPfNombre(0);
     mdl.setrDABMontant(0);
     mdl.setrDABNombre(0);
     mdl.setRmMontant(MOBIDINAR_POSTE_TTCASH);
     mdl.setRmNombre(nmbr_poste);
     x.add(mdl);
	 }catch(Exception e) {
    	 System.out.println("Vérifier Le Fichier !!");
     }
	 return x;
	    }
	    
////////////////FUNCTION POUR COUNTER LE NOMBRE JOURNALIER TOTAL//////////////////////////////////////////
	    public int getTotal(BufferedReader file) {
	    	boolean first=true;
	    	int total=0;
	    	String line;
	    	try {
	    	while((line = file.readLine()) != null){
	    	      if(first){
	    	    	  first=false;
	    	      }else{
	    	    	  total++;
	    	      }
	    	}}catch(Exception e) {
	    		System.out.println("Erreur De Saisir Le Fichier !!");
	    	}
	    	return total;
	    }
	    
///////////////////////////FUNCTION POUR RETOURNE LES VARIABLES/////////////////////////////////////////////////
	   public MyModel getByName(String name,List<MyModel> x) {
		  for(int i=0;i<x.size();i++) {
			  if(x.get(i).getName()==name) {
				  return x.get(i);
			  }
		  }
		  return new MyModel();
	   }
	   
////////////////////////FONCTION CREER ET MODIFIER LES FICHIERS..////////////////////////////////////////////////////
	   public void createFile(List<MyModel> x,String newDate) {
		   String path1="C:\\Users\\hp-pavilion\\eclipse-workspace\\rdfile3\\src\\Total_Jounalier\\";
	        try {
	    	File atb = new File(path1+"TOTAL_ATB_"+newDate+".txt");
	        
	            atb.createNewFile();
	            FileWriter fw = new FileWriter(atb);
	            PrintWriter pw =new PrintWriter(fw);
	            pw.println(Remplir1(1)+";"+convrt2(getByName("ATB",x).getRmNombre()/*nmbr_atb*/)+";"+convrt1(getByName("ATB",x).getRmMontant()/*MPAYMENT_ATB_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(7)+";"+Remplir1(12)+" ATB        ;"+JJMM(JJMMAAAA(newDate))+Remplir1(17));
	            pw.println(Remplir1(1)+";"+convrt2(getByName("ATB",x).getPfNombre()/*nmbr_atbf*/)+";"+convrt1(getByName("ATB",x).getPfMontant()/*MPAYMENT_ATB_PFACT*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(8)+";"+Remplir1(13)+" ATB;"+JJMM(JJMMAAAA(newDate))+Remplir1(18));
	            pw.println(Remplir1(1)+";"+convrt2(getByName("ATB",x).getrDABNombre()/*nmbr_dab*/)+";"+convrt1(getByName("ATB",x).getrDABMontant()/*DAB_ATB_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(9)+";"+Remplir1(14)+" ATB           ;"+JJMM(JJMMAAAA(newDate))+Remplir1(19));
	            pw.println(Remplir1(1)+";"+convrt2(0)+";"+convrt1(0)+";"+JJMMAAAA(newDate)+";"+Remplir1(10)+";"+Remplir1(15)+" ATB   ;"+JJMM(JJMMAAAA(newDate))+Remplir1(20));
	            pw.close();
	        
			
	        File bh = new File(path1+"TOTAL_BHA_"+newDate+".txt");
	        
	            bh.createNewFile();
	            FileWriter fw1 = new FileWriter(bh);
	            PrintWriter pw1 =new PrintWriter(fw1);
	            pw1.println(Remplir1(3)+";"+convrt2(getByName("BH",x).getRmNombre()/*nmbr_bh*/)+";"+convrt1(getByName("BH",x).getRmMontant()/*MPAYMENT_BHB_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(7)+";"+Remplir1(12)+" BH        ;"+JJMM(JJMMAAAA(newDate))+Remplir1(17));
	            pw1.println(Remplir1(3)+";"+convrt2(getByName("BH",x).getPfNombre()/*nmbr_bhf*/)+";"+convrt1(getByName("BH",x).getPfMontant()/*MPAYMENT_BHB_PFACT*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(8)+";"+Remplir1(13)+" BH;"+JJMM(JJMMAAAA(newDate))+Remplir1(18));
	            pw1.println(Remplir1(3)+";"+convrt2(getByName("BH",x).getrDABNombre()/*nmbr_dab*/)+";"+convrt1(getByName("BH",x).getrDABMontant()/*DAB_ATB_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(9)+";"+Remplir1(14)+" BH           ;"+JJMM(JJMMAAAA(newDate))+Remplir1(19));
	            pw1.println(Remplir1(3)+";"+convrt2(0)+";"+convrt1(0)+";"+JJMMAAAA(newDate)+";"+Remplir1(10)+";"+Remplir1(15)+" BH   ;"+JJMM(JJMMAAAA(newDate))+Remplir1(20));
	            pw1.close();
	        
		    
	        
	        File smt = new File(path1+"TOTAL_SMT_"+newDate+".txt");
	       
	            smt.createNewFile();
	            FileWriter fw2 = new FileWriter(smt);
	            PrintWriter pw2 =new PrintWriter(fw2);
	            pw2.println(Remplir1(4)+";"+convrt2(getByName("SMT",x).getRmNombre()/*nmbr_smt*/)+";"+convrt1(getByName("SMT",x).getRmMontant()/*TELCOMONEY_SMT_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(7)+";"+Remplir1(12)+" SMT        ;"+JJMM(JJMMAAAA(newDate))+Remplir1(17));
	            pw2.println(Remplir1(4)+";"+convrt2(getByName("SMT",x).getPfNombre()/*nmbr_smtf*/)+";"+convrt1(getByName("SMT",x).getPfMontant()/*TELCOMONEY_SMT_PFACT*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(8)+";"+Remplir1(13)+" SMT;"+JJMM(JJMMAAAA(newDate))+Remplir1(18));
	            pw2.println(Remplir1(4)+";"+convrt2(0)+";"+convrt1(0)+";"+JJMMAAAA(newDate)+";"+Remplir1(9)+";"+Remplir1(14)+" SMT           ;"+JJMM(JJMMAAAA(newDate))+Remplir1(19));
	            pw2.println(Remplir1(4)+";"+convrt2(0)+";"+convrt1(0)+";"+JJMMAAAA(newDate)+";"+Remplir1(10)+";"+Remplir1(15)+" SMT   ;"+JJMM(JJMMAAAA(newDate))+Remplir1(20));
	            pw2.close();
	        
	       
	        File stb = new File(path1+"TOTAL_STB_"+newDate+".txt");
	        
	            stb.createNewFile();
	            FileWriter fw3 = new FileWriter(stb);
	            PrintWriter pw3 =new PrintWriter(fw3);
	            pw3.println(Remplir1(2)+";"+convrt2(getByName("STB",x).getRmNombre()/*nmbr_stb*/)+";"+convrt1(getByName("STB",x).getRmMontant()/*MPAYMENT_STB_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(7)+";"+Remplir1(12)+" STB        ;"+JJMM(JJMMAAAA(newDate))+Remplir1(17));
	            pw3.println(Remplir1(2)+";"+convrt2(getByName("STB",x).getPfNombre()/*nmbr_stb*/)+";"+convrt1(getByName("STB",x).getPfMontant()/*MPAYMENT_STB_PFACT*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(8)+";"+Remplir1(13)+" STB;"+JJMM(JJMMAAAA(newDate))+Remplir1(18));
	            pw3.println(Remplir1(2)+";"+convrt2(getByName("STB",x).getrDABNombre()/*nmbr_dab*/)+";"+convrt1(getByName("STB",x).getrDABMontant()/*DAB_ATB_TTCASH*/)+";"+JJMMAAAA(newDate)+";"+Remplir1(9)+";"+Remplir1(14)+" STB           ;"+JJMM(JJMMAAAA(newDate))+Remplir1(19));
	            pw3.println(Remplir1(2)+";"+convrt2(0)+";"+convrt1(0)+";"+JJMMAAAA(newDate)+";"+Remplir1(10)+";"+Remplir1(15)+" STB   ;"+JJMM(JJMMAAAA(newDate))+Remplir1(20));
	            pw3.close();
	        
	        }catch(Exception e) {
	        	
	        }
	        
	    }
	   
////////////FONCTION REMPLIR LES FICHIERS..
       public static String Remplir1 (int x){
    	   String Config_Parameters = null;
    	   int i=0;
    	   try {
    	   String ConfigParameter ="C://Users//hp-pavilion//eclipse-workspace//rdfile3//src//Config_Parameters//Config_Parameters.txt";
    	   BufferedReader ConfigParameters = new BufferedReader(new FileReader(ConfigParameter));
    	   while(i!=x) {
	        	 Config_Parameters = ConfigParameters.readLine(); 
	        	i++;
	        }
	        ConfigParameters.close();
    	   }catch(Exception e) {
	        	System.out.println("verifier le chemin de Config_Parameters");
	        }
    	   return Config_Parameters.split("=")[1];
	    }
       
////////////FONCTION REMPLIR LE DATE GLOBAL DE LA FICHIER..
       public static String JJMMAAAA (String str){
    	   
    	   return str.substring(str.length()-2,str.length())+str.substring(str.length()-4,str.length()-2)+str.substring(0,str.length()-4);
       }
/////////////////////////////////
 public static String JJMMAAAAToString (String str){
    	   
    	   return str.substring(str.length()-2,str.length())+"-"+str.substring(str.length()-4,str.length()-2)+"-"+str.substring(0,str.length()-4);
       }
  
////////////FONCTION REMPLIR LE DATE GLOBAL DE LA FICHIER..
       public static String JJMM (String x){
    	   return x.substring(0,x.length()-4);
       }
       
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class Dynamic_Median implements Comparator<City>{
    PQ greaterthan;
    PQ lessthan ;

    //Ousiastika kratei mia priority queue lessthan  pou to megalytero  density einai root kai alli mia greaterthan pou to mikrotero density einai to root.
    //sthn less than einai oi polis me mikrotero density apo to median kai sthn greater me megalytero 
    public void givePriority(City c1){

            lessthan.insert(c1);
            greaterthan.insert(lessthan.getmin());
            while (greaterthan.size() > lessthan.size() + 1) {
                lessthan.insert(greaterthan.getmin());;
            }
          
    }
    //an einai artios arithmos apo polis emfanizei to mikrotero median 
    public City getMedian(){
        if(lessthan.size() == greaterthan.size()){
            return  (City)lessthan.min();
        }else{
            return (City)greaterthan.min();
        }

        
    }
    //edw dokimasa na valw to comparator aftou tou arxeiou alla den ginotan.logika exw kanei kati lathos sthn ylopoihsh 
    // logika tha einai lathos etsi opws einai twra giati xrisimopoio ton comparator toy prohgoymenoy meroys ths ergasias 
    Dynamic_Median(){
        greaterthan = new PQ<>(this.reversed());
        lessthan  = new PQ<>(this);
    }

    public static Boolean isCharNumber(char[] numbers,char c){ // check if a char given is a number from 0..9
        for(char num : numbers){
            if(c==num){
                return true;
            }
        }
        return false;
    }
    

    public static void main(String[] args) {
        Dynamic_Median Dm = new Dynamic_Median();
        Scanner in = new Scanner(System.in);
        String path;
        //if path isnt given as argument get default
        if(args.length==0){
            path = "Ergasiadd2/cases.txt";
        }else{
            path = args[0];
        }
        File  file = new File(path);
        int iter = 0 ;
        try{
            BufferedReader br  = new BufferedReader(new FileReader(file));
            String st;
            char [] ch ; 
            String id = ""; 
            String name ="";
            String  population = "" ; 
            String influenza_cases= "" ;
            char numbers [] ={'1','2','3','4','5','6','7','8','9','0',' ','-'};
            Boolean add_space = false;
            int field ;
            while((st = br.readLine())!= null){  
                ch = st.toCharArray(); 
                field = 0 ;                 
                for(char c : ch){
                    if(field ==2  && !isCharNumber(numbers, c)){ // check if name has spaces in the form Buenos Aires continue when next char  is a number 
                        field-=1;
                        add_space = true; 
                    }
                    if(c == ' '){
                        field+=1;
                        continue; 
                    }
                    switch (field) {
                        case 0:
                        id += c ;
                        break;
                        case 1:
                        name+=c; 
                        break;
                        case 2: 
                        population += c;
                        break;
                        case 3: 
                        influenza_cases +=c;
                        break;
                        default:
                            break;
                    }
                }
                CityInterface city = new City();
                city.setID(Integer.parseInt(id));
                city.setPopulation(Integer.parseInt(population));
                city.setInfluenzaCases(Integer.parseInt(influenza_cases));
                city.setName(name);
                Dm.givePriority((City)city);
                iter++;
                if(iter%5==0){
                     System.out.println(Dm.getMedian());
                }
                id = "";
                name = "";
                population = "";
                influenza_cases = "";
                
                
            }
          
            
        }catch(IOException e ){
            e.printStackTrace();
        }
    }

        
    
    @Override
    public int compare(City t1, City t2) {
        return (int)(t2.getDensity() - t1.getDensity());
    }
}


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
        greaterthan = new PQ<>(new DynamicInfluenza_k_withPQ().reversed());
        lessthan  = new PQ<>( new DynamicInfluenza_k_withPQ());
    }



    public static void main(String[] args) {
        Dynamic_Median Dm = new Dynamic_Median();
        Scanner in = new Scanner(System.in);
        File  file = new File("cases.txt");
        int iter = 0 ;
        try{
            BufferedReader br  = new BufferedReader(new FileReader(file));
            String st;
            char [] ch ; 
            String id = ""; 
            String name ="";
            String  population = "" ; 
            String influenza_cases= "" ;
            //Den kserw giati exw valei to date nomiza oti oi times pou edine sto txt opws px {— 0.00129} eixan kati na kanoun me to date omws den katalava pote ti einai opote einai   
            String  date  = "" ;
            int field ;
            while((st = br.readLine())!= null){  
                ch = st.toCharArray(); 
                field = 0 ;                 
                for(char c : ch){
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
                        case 5:
                        date += c; 
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
                date = "";
                
            }
          
            
        }catch(IOException e ){
            e.printStackTrace();
        }
    }

        
    
    @Override
    public int compare(City t1, City t2) {
        return (int)(t1.getDensity() - t2.getDensity());
    }
}


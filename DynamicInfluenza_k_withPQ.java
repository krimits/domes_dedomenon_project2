
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.util.Comparator;



public class DynamicInfluenza_k_withPQ  implements Comparator<City>{


public static PQ replaceifBigger(PQ pq , City city ){
    if(((City)(pq.min())).compareTo(city) > 0 ){
        pq.getmin();
        pq.insert(city);
    }
    return pq ;
}

public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(System.in);
        System.out.print("Input k  = ");
        int k =  in.nextInt(); 
        System.out.println("\n");
        File  file = new File("cases.txt");
        try{
            BufferedReader br  = new BufferedReader(new FileReader(file));
            String st;
            char [] ch ; 
            String id = ""; 
            String name ="";
            String  population = "" ; 
            String influenza_cases= "" ;
            //Den kserw giati exw valei to date nomiza oti oi times pou edine sto txt opws px {— 0.00129} eixan kati na kanoun me to date omws den katalava pote ti einai opote einai   
            //String  date  = "" ;
            int field ;
            PQ pq= new PQ<City>(new DynamicInfluenza_k_withPQ());
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
                        //case 5:
                        //date += c; 
                       // break;
                        default:
                            break;
                    }
                }
                CityInterface city = new City();
                city.setID(Integer.parseInt(id));
                city.setPopulation(Integer.parseInt(population));
                city.setInfluenzaCases(Integer.parseInt(influenza_cases));
                city.setName(name);

                if(pq.size()<k){   
                    pq.insert((City)city);       
                }else{ 
                   pq =  replaceifBigger(pq,(City)city);
                }
                id = "";
                name = "";
                population = "";
                influenza_cases = "";
                //date = "";
                
            }
            while (!pq.isEmpty()) {
               System.out.println(pq.getmin()); 
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
 
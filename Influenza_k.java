
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Influenza_k{   
    static ArrayList<CityInterface> swap(ArrayList<CityInterface> cities,int ct1 ,int ct2  ){
        CityInterface  temp  =  cities.get(ct1);
        cities.set(ct1, cities.get(ct2));
        cities.set(ct2,temp);
        return cities;

    }

    static int  partition(ArrayList<CityInterface> cities, CityInterface low ,CityInterface  high ){
        int pivot = cities.indexOf(high);
        int i = cities.indexOf(low) - 1;
        for(int j = cities.indexOf(low); j < cities.indexOf(high); j++ ){
            City cmp1 = (City)cities.get(j);
            City cmp2 = (City)cities.get(pivot);
            if(cmp1.compareTo(cmp2)<0){
                i++;
                swap(cities, i, j);
            }
        }
        swap(cities, i+1,cities.indexOf(high));
        return(i+1);
    } 

    public static  ArrayList<CityInterface> QuickSort(ArrayList<CityInterface>cities,int low, int high){
        if(low < high){
            int pi = partition(cities, cities.get(low), cities.get(high));
            cities= QuickSort(cities,low,pi-1);
            cities = QuickSort(cities,pi+1,high);
        }
        return cities;
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
            String  date  = "" ;
            int field ;
            ArrayList<CityInterface> cities = new ArrayList<>();
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
                
                cities.add(city);
                id = "";
                name = "";
                population = "";
                influenza_cases = "";
                date = "";
                
            }
            
            if(k > cities.size()){
                System.out.println("EXITING PROGRAM Number k  > Population ");
                System.exit(0);
            }
            cities = QuickSort(cities, 0, cities.size()-1);
            System.out.println("The top k cities are:");
            for(int i =0 ; i<k ;i++){
                System.out.println(cities.get(i));
            }

        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    

    
}
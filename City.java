
public class City implements CityInterface,Comparable{
    private int ID;  
    private String name;
    private int population;
    private int InfluenzaCases;
    
    public int getID(){
        return ID; 
    }
    public String getName(){
        return  name;
    }
    public int getPopulation()
    {
        return population;
    }
    public int getInfluenzaCases()
    {
        return InfluenzaCases;
    }
    public void setID(int ID)
    {
        if(ID>=0 && ID<=99){
            this.ID = ID;
        }else{
             System.out.println("ID OUT OF BOUNDS");
        }
    }
    public void setName(String name)
    {
        if(name.length()<=50&&name.length()>0){
            this.name = name; 
        }else{
            System.out.println("STRING SIZE OUT OF BOUNDS");
        }
    }
    public void setPopulation(int population)
    {
        if(population<Math.pow(10,7)&& population>0){
            this.population = population;
        }else{
            System.out.println("POPULATION SIZE OUT OF BOUNDS ");
        }
        
    }
    public void setInfluenzaCases(int InfluenzaCases)
    {
        if(population>=InfluenzaCases){
            this.InfluenzaCases = InfluenzaCases ;
        }else{
            System.out.println("INFLUENZACASES MORE THAN POPULATION");
        }         
    }

    public double getDensity()
    {
        double density = InfluenzaCases/(population/ (5 * Math.pow(10, 4)));
        return density;
    }

    public String toString(){
        return name;
    }
    
    @Override
    public int compareTo(Object o) {
        City city  = (City)o;
        if(getDensity() - city.getDensity()==0){
            if(name.compareTo(city.getName())>0){
                return -1; 
            }else if ( name.compareTo(city.getName())<0){
                return 1; 
            }else{
                if(ID < city.getID()){
                    return -1; 
                }else{
                    return 1;
                }
            }
        }else{
            return (int)(getDensity() -  city.getDensity());
        }
    }
    


    
}

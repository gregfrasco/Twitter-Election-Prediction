/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frascog
 */
public class Politicians {
    
    private List<Politicain> politicains;

    public Politicians() {
        this.politicains = new ArrayList<Politicain>();
        Politicain hillery = new Politicain("Hillary Clinton", "src/Images/clinton.jpg", Party.democrat);
        Politicain sanders = new Politicain("Bernie Sanders", "src/Images/snaders.jpg", Party.democrat);
        Politicain trump = new Politicain("Donald Trump", "src/Images/trump.jpg", Party.republican);
        Politicain carson = new Politicain("Ben Carson", "src/Images/carson.jpg", Party.republican);
        Politicain rubio = new Politicain("Marco Rubio", "src/Images/rubio.jpg", Party.republican);
        
        politicains.add(hillery);
        politicains.add(sanders);
        politicains.add(trump);
        politicains.add(carson);
        politicains.add(rubio);
    }

    public List<Politicain> getPoliticains() {
        return politicains;
    }
    
}

package pckElevator_V1;

public class Floor implements IElement {

    private String label;
    
    
    // Constructor ********************
    public Floor ( String label ) {
        this.label = label;
    }
    
    
    @Override
    public void accept( IVisitor visitor ) {
        visitor.visit( this );
    }
    
    // ***** recomended add from class conversation
    @Override
    public void release( IVisitor visitor ){
      // THIS NEEDS TO BE FIGERED OUT 
    }
        
    public void arrived( int floor ){ 
        // needs to be finished
    }
    
    //*************FROM MVC DEMO ***************
    //@Override
    public String getLabel() {
        return label;
    }

    
}// class Floor

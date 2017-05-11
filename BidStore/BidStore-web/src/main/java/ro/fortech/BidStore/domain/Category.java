package ro.fortech.BidStore.domain;

import java.io.Serializable;
 
public class Category implements Serializable, Comparable<Category> {
 
    private String name;
     
    private String description;
    private final static String bigDescription = "Lorem Ipsum este pur şi simplu o machetă pentru text a industriei tipografice. Lorem Ipsum a fost macheta standard a industriei încă din secolul al XVI-lea, când un tipograf anonim a luat o planşetă de litere şi le-a amestecat pentru a crea o carte demonstrativă pentru literele respective. Nu doar că a supravieţuit timp de cinci secole, dar şi a facut saltul în tipografia electronică practic neschimbată. A fost popularizată în anii '60 odată cu ieşirea colilor Letraset care conţineau pasaje Lorem Ipsum, iar mai recent, prin programele de publicare pentru calculator, ca Aldus PageMaker care includeau versiuni de Lorem Ipsum."; 
    private String ctgrId;
    
    public Category(String name) {
    	this.name = name;
        this.description = "Test description for "+name+System.lineSeparator()+bigDescription;
    }
    
    public Category(String name, String ctgrId) {
    	this.name = name;
        this.description = "Test description for "+name+System.lineSeparator()+bigDescription;
        this.ctgrId = ctgrId;
    }
    
    public Category(String name, String description, String ctgrId) {
        this.name = name;
        this.description = description;
        this.ctgrId = ctgrId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getdescription() {
        return description;
    }
 
    public void setdescription(String description) {
        this.description = description;
    }
 
    public String getctgrId() {
        return ctgrId;
    }
 
    public void setctgrId(String ctgrId) {
        this.ctgrId = ctgrId;
    }
 
    //Eclipse Generated hashCode and equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((ctgrId == null) ? 0 : ctgrId.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (ctgrId == null) {
            if (other.ctgrId != null)
                return false;
        } else if (!ctgrId.equals(other.ctgrId))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return name;
    }
 
    public int compareTo(Category category) {
        return this.getName().compareTo(category.getName());
    }
}  
package ro.fortech.BidStore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import ro.fortech.BidStore.domain.Item;
 
@ManagedBean(name = "itemService")
@ApplicationScoped
public class ItemService {
    
	private final static String bigDescription = "Lorem Ipsum este pur şi simplu o machetă pentru text a industriei tipografice. Lorem Ipsum a fost macheta standard a industriei încă din secolul al XVI-lea, când un tipograf anonim a luat o planşetă de litere şi le-a amestecat pentru a crea o carte demonstrativă pentru literele respective. Nu doar că a supravieţuit timp de cinci secole, dar şi a facut saltul în tipografia electronică practic neschimbată. A fost popularizată în anii '60 odată cu ieşirea colilor Letraset care conţineau pasaje Lorem Ipsum, iar mai recent, prin programele de publicare pentru calculator, ca Aldus PageMaker care includeau versiuni de Lorem Ipsum."; 
	
	public List<Item> createItems(int i) {
		
		List<Item> items = new ArrayList<Item>();
		
		for (i=1;i<=100;i++) {
			items.add(new Item(i, "Item"+i, "Photo"+i, bigDescription, i*10, new Date(System.currentTimeMillis()+1000*60*60*24*10), i));
		}
		
		return items;
	}
    
}
package ro.fortech.BidStore.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
			items.add(new Item(i, "Item"+i, "Photo"+(100-1-i), bigDescription, i*10, new Date(System.currentTimeMillis()+1000*60*60*24*10), i));
		}
		
		return items;
	}

	public List<Item> list(int firstRow, int rowsPerPage, String sortField, boolean sortAscending) {

		List<Item> items = new ArrayList<Item>();
		
		for (int i=firstRow;i<rowsPerPage+firstRow;i++) {
			items.add(new Item(i, "Item"+i, "Photo"+(100-1-i), bigDescription, i*10, new Date(System.currentTimeMillis()+1000*60*60*24*10), i));
		}
		if(!sortAscending) Collections.reverse(items);
		
		Collections.sort(items, new CustomComparator<Item>(sortField));
		
		return items;
		
	}

	public int count() {
		return 100;
	}
    
}

class CustomComparator<T>  implements Comparator<Item> {
	
	String field;
	
	CustomComparator (String field) {
		this.field = field;
	}
	
	@Override
	public int compare(Item o1, Item o2) {
		try {
			Method getter = new PropertyDescriptor(field, Item.class).getReadMethod();
			return ((String) getter.invoke(o1)).compareTo((String) getter.invoke(o2));
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return 0;
		}
			}
	
}
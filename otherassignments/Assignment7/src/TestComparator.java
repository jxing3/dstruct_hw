import java.util.Comparator;

    public class TestComparator implements Comparator{
    	
		@Override
		public int compare(Object o1, Object o2) {
			return -1 * ((Comparable)o1).compareTo(o2);
		}
    	
    }

package zu.jxls.jxlsnofault;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * This class wraps an object that JXLS could use to render an Excel.
 * Since it's an Iterable object, JXLS doesn't raise an exception if
 * some prop isn't valuable.
 * 
 * See JXLS GridCommand class used from SimpleExporter
 * https://bitbucket.org/leonate/jxls/src/65fb5b705a70a8d8d3971e2abb8ec665d47434eb/src/main/java/org/jxls/command/GridCommand.java?at=r2.4.3&fileviewer=file-view-default
 * 
 * At row 163 if the object is Iterable then the evaluation is more simple otherwise al line 190 PropertyUtils.getProperty() may throw an Exception
 * for instance when you can to access to a child object property when the child object is null.
 * see row 197 and 199 with its message: "Failed to evaluate property ...  of row object of class ..."
 *  
 * @author Luciano Zu
 *
 */
public class IterableWrapper implements Iterable<Object> {

	private Object data;
	private List<String> fieldNames;
	
	public IterableWrapper(Object data, List<String> fieldNames) {
		super();
		this.data = data;
		this.fieldNames = fieldNames;
	}

	@Override
	public Iterator<Object> iterator() {
		return new InnerIterator(data, fieldNames.iterator());
	}
	
	private class InnerIterator implements Iterator<Object> {

		private Object data;
		private Iterator<String> fieldNames;
		
		public InnerIterator(Object data, Iterator<String> fieldNames) {
			super();
			this.data = data;
			this.fieldNames = fieldNames;
		}

		@Override
		public boolean hasNext() {
			return fieldNames.hasNext();
		}

		@Override
		public Object next() {
			String field = fieldNames.next();
			
			Object value = null;
			
			// Try to evaluate the property, if you fail, don't worry return null and proceed with Excel rendering.
			try {
				value = PropertyUtils.getProperty(data, field);
			} catch (Exception e) {
			}
			
			return value;
		}
		
	}
}

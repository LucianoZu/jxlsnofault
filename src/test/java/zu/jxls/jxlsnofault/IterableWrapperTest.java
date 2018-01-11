package zu.jxls.jxlsnofault;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.jxls.template.SimpleExporter;

public class IterableWrapperTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void shouldRaiseAnException() {
		SimpleExporter exporter = new SimpleExporter();
		
		List<String> propNames = Arrays.asList("name", "firstChild.name", "secondChild.name");
		
		exception.expect(IllegalStateException.class);
		exception.expectMessage("Failed to evaluate property secondChild.name of row object of class zu.jxls.jxlsnofault.ParentBean");
		
		exporter.gridExport( Arrays.asList("Parent Name", "First Child Name", "Second Child Name")
							,Arrays.asList(new ParentBean())
							,propNames.stream().reduce((x, y) -> x.concat(", " + y)).orElse("")
							,new ByteArrayOutputStream());
	}

	@Test
	public void shouldNotRaiseAnException() {
		SimpleExporter exporter = new SimpleExporter();
		
		List<String> propNames = Arrays.asList("name", "firstChild.name", "secondChild.name");
				
		exporter.gridExport( Arrays.asList("Parent Name", "First Child Name", "Second Child Name")
							,Arrays.asList(new IterableWrapper(new ParentBean(), propNames))
							,propNames.stream().reduce((x, y) -> x.concat(", " + y)).orElse("")
							,new ByteArrayOutputStream());
	}

}

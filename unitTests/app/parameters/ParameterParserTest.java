package app.parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class ParameterParserTest {

	private final String hantivirusesFileName = "unitTestFiles" + File.separator + "reconstruction" + File.separator + "solver" + File.separator + "realExamples" + File.separator + "Hantaviruses2013.nex";	
	
	@Test
	public void ImprovedNodeMappingTest() {
		String[] args = new String[] {"--CostScheme", "@{0,1,1,2}", "--Algorithm", "NM", "--File", hantivirusesFileName };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(3, parameters.size());
		assertEquals(true, parser.IsValidParameterSet());
	}
	
	@Test
	public void ImprovedNodeMappingWithMetaHeursitcTest() {
		String[] args = new String[] {"--CostScheme", "@{0,1,1,2}", "--Algorithm", "NM", "--File", hantivirusesFileName, "--MetaHeuristic", "GA", "--MetaHeuristicSettings", "@{100, 100}" };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(5, parameters.size());
		assertEquals(true, parser.IsValidParameterSet());
	}	
	
	@Test
	public void RascalTest() {
		String[] args = new String[] {"--CostScheme", "@{0,1,1,2}", "--Algorithm", "Rascal", "--File", hantivirusesFileName, "--RascalSampling", "Log", "--RascalStrategy", "Random" };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(5, parameters.size());
		assertEquals(true, parser.IsValidParameterSet());
	}		
	
	@Test
	public void TreeCollapseTest() {
		String[] args = new String[] {"--CostScheme", "@{0,1,1,2}", "--Algorithm", "TreeCollapse", "--File", hantivirusesFileName, "--MultiThreaded", "True" };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(4, parameters.size());
		assertEquals(true, parser.IsValidParameterSet());
	}	
	
	@Test
	public void IsNotValidParameterSetTest1() {
		String[] args = new String[] {"--CostScheme", "0,1,1,2", "--Algorithm", "NM", "--File", hantivirusesFileName };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(3, parameters.size());
		assertEquals(false, parser.IsValidParameterSet());
	}	
	
	@Test
	public void IsNotValidParameterSetTest2() {
		String[] args = new String[] {"--CostScheme", "0", "--Algorithm", "NM", "--File", hantivirusesFileName };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(3, parameters.size());
		assertEquals(false, parser.IsValidParameterSet());
	}	
	
	@Test
	public void IsNotValidParameterSetTest3() {
		String[] args = new String[] {"--CostScheme", "@{0,1,1,2", "--Algorithm", "NM", "--File", hantivirusesFileName };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(3, parameters.size());
		assertEquals(false, parser.IsValidParameterSet());
	}	
	
	@Test
	public void IsNotValidParameterSetTest4() {
		String[] args = new String[] {"--CostScheme", "{0,1,1,2}", "--Algorithm", "NM", "--File", hantivirusesFileName };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(3, parameters.size());
		assertEquals(false, parser.IsValidParameterSet());
	}	
	
	@Test
	public void IsNotValidParameterSetTest6() {
		String[] args = new String[] {"--CostScheme", "@{0,1,1,2}", "--File", hantivirusesFileName };
		ParameterParser parser = new ParameterParser(args);
		List<Parameter> parameters = parser.GetParameters();
		assertNotNull(parameters);
		assertEquals(2, parameters.size());
		assertEquals(false, parser.IsValidParameterSet());
	}
}

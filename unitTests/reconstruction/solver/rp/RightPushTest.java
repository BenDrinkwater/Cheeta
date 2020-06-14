package reconstruction.solver.rp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import reconstruction.solver.tc.LossCounter;

public class RightPushTest extends RightPushTestBase {

	// all tests in this class will fail until we implement RightPush
	
	@Test
	public void CompletePushForwardTest1() {
		LoadTestCase1WithFirstOrderingExample();
		RightPush rightPush = new RightPush(this.testCase1, this.testCase1Scenerio);
		LossCounter counter = new LossCounter(this.testCase1, rightPush.getLossMinimisedParasiteMapping());
		assertEquals(0, counter.countNumberOfLossEvents());			
	}

	@Test
	public void CompletePushForwardTest2() {
		LoadTestCase1WithThirdOrderingExample();
		RightPush rightPush = new RightPush(this.testCase1, this.testCase1Scenerio);
		LossCounter counter = new LossCounter(this.testCase1, rightPush.getLossMinimisedParasiteMapping());
		// 2 losses are due to a duplication event. May include a feature for this later
		assertEquals(2, counter.countNumberOfLossEvents());	
	}	
	
	@Test
	public void CompletePushForwardTest3() {
		LoadTestCase2WithFirstOrderingExample();
		RightPush rightPush = new RightPush(this.testCase2, this.testCase2Scenerio);
		LossCounter counter = new LossCounter(this.testCase2, rightPush.getLossMinimisedParasiteMapping());
		assertEquals(2, counter.countNumberOfLossEvents());	
	}	
	
	@Test
	public void CompletePushForwardTest4() {
		LoadTestCase2WithSecondOrderingExample();
		RightPush rightPush = new RightPush(this.testCase2, this.testCase2Scenerio);
		LossCounter counter = new LossCounter(this.testCase2, rightPush.getLossMinimisedParasiteMapping());
		assertEquals(2, counter.countNumberOfLossEvents());
	}	
	
	@Test
	public void PartailPushForwardTest1() {
		LoadTestCase1WithSecondOrderingExample();
		RightPush rightPush = new RightPush(this.testCase1, this.testCase1Scenerio);
		LossCounter counter = new LossCounter(this.testCase1, rightPush.getLossMinimisedParasiteMapping());
		assertEquals(2, counter.countNumberOfLossEvents());			
	}		
	
}

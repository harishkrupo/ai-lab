import java.util.ArrayList;
import java.util.Iterator;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TheBoard b=new TheBoard("12345678 ");
//		TheBoard b=new TheBoard(" 12345678");
		
//		String init = " 13425786";
//		String init = "8134 2765";
		String init = "7245 6831";	
		
//		System.out.println(b.PossibleNextStates(init));
//		BfsSol sol= new BfsSol(b, init);
//		DfsSol sol = new DfsSol(b,init);
//		DfsDepthLimited sol = new DfsDepthLimited(b, init, 2);
//		GreedySol sol= new GreedySol(b, init);
		AStarSol sol = new AStarSol(b, init);
		
		ArrayList<Node> searched = sol.findSol();
		Node finalState = sol.getState();
		Node t=finalState;
		System.out.println("The path to the solution : ");
		while(t!=null)
		{
			System.out.println(t);
			t=t.parent;
		}
		
		System.out.println("Searched configurations : ");
		Iterator<Node> it = searched.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}

}

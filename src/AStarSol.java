import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class AStarSol {
	private Node init;
	private TheBoard board;
	private PriorityQueue<Node> fringe;
	
	
	public AStarSol(TheBoard b, String initializer)
	{
		board=b;
		fringe = new PriorityQueue<Node>();
		this.init=new Node();
		this.init.state=initializer;
		this.init.parent=null;
		fringe.add(init);
	}
	
	public ArrayList<Node> findSol()
	{
		ArrayList<Node> allSearched = new ArrayList<Node>();
		ArrayList<String> nextStates=null;
		
		int i;
		while(true)
		{
			if(fringe.isEmpty())
			{
				allSearched=null;
				break;
			}
			if(board.isFinal(fringe.peek().state))
			{
				break;
			}
			Node staten = fringe.remove();
			allSearched.add(staten);
			nextStates=board.PossibleNextStates(staten.state);
			for(i=0;i<nextStates.size();i++)
			{
				Iterator<Node> it=fringe.iterator();
				Iterator<Node> ita = allSearched.iterator();
				boolean found = false;
				while(it.hasNext() || ita.hasNext())
				{
					String t = it.hasNext() ? it.next().state : new String("");
					String p = ita.hasNext() ? ita.next().state : new String("");
					if(t.compareTo(nextStates.get(i))==0 || p.compareTo(nextStates.get(i))==0)
					{
						found = true;
						break;
					}
				}
				if(found==false)
				{
					Node t= new Node();
					t.parent=staten;
					t.state=nextStates.get(i);
					t.priority = calculateManhattan(t.state);
					fringe.add(t);
				}
			}
		}
		
		return allSearched;
	}
	
	private int calculateManhattan(String state)
	{
		String goalstate = board.getGoal();
		int sum = 0;
		int i;
		for(i=0;i<state.length();i++)
		{
			if(state.charAt(i)!=' ')
			{
				int gi = goalstate.indexOf(state.charAt(i));
				int xi = i/3;
				int yi = i%3 -1 ;
				int gxi = gi/3;
				int gyi = gi%3 -1 ;
				sum = sum + Math.abs(xi-gxi) + Math.abs(yi-gyi); 
			}
		}
		return sum;
	}

	
	public Node getState() {
		return fringe.peek();
	}
	

}

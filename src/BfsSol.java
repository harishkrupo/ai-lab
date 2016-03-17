import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BfsSol {
	private Node init;
	private TheBoard board;
	private LinkedList<Node> fringe;
	
	public BfsSol(TheBoard b, String initializer)
	{
		board=b;
		fringe = new LinkedList<Node>();
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
//			System.out.println(staten);
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
					fringe.add(t);
				}
			}
		}
		
		return allSearched;
	}
	
	public Node getState() {
		return fringe.peek();
	}
}

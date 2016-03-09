import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class DfsDepthLimited {
	private Node init;
	private TheBoard board;
	private Stack<Node> fringe;
	private int cdepth;
	private int maxdepth;
	
	public DfsDepthLimited (TheBoard b, String initializer,int _maxdepth)
	{
		board=b;
		cdepth=0;
		fringe = new Stack<Node>();
		this.init=new Node();
		this.init.state=initializer;
		this.init.parent=null;
		this.init.depth=cdepth;
		cdepth=cdepth+1;
		fringe.add(init);
		maxdepth=_maxdepth;
	}
	
	public ArrayList<Node> findSol()
	{
		ArrayList<Node> allSearched = new ArrayList<Node>();
		ArrayList<String> nextStates=null;
		
		int i;
		while(true)
		{
			if(fringe.empty())
			{
				allSearched=null;
				break;
			}
			String ti = fringe.peek().state;
			if(board.isFinal(ti ))
			{
				break;
			}
			Node staten = fringe.pop();
			cdepth = staten.depth;
			allSearched.add(staten);
			if(cdepth<maxdepth)
			{
				cdepth = cdepth+1;
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
						t.depth=cdepth;
						t.state=nextStates.get(i);
						fringe.push(t);
					}
				}
			}
		}
		
		return allSearched;
	}
	
	public Node getState() {
		return fringe.peek();
	}

}

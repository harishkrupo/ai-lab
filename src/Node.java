
public class Node {
	public String state;
	public Node parent;
	public int depth;
	
	@Override
	public String toString()
	{
		return state.substring(0, 3)+"\n"+state.substring(3, 6)+"\n"+state.substring(6, 9)+ "\n";
	}
}

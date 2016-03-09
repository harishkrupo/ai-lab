import java.util.ArrayList;

public class TheBoard {
	private String goalString;

	public TheBoard() {}
	
	public TheBoard(String goal)
	{
		goalString=goal;
	}
	
	public ArrayList<String> PossibleNextStates(String state)
	{
		ArrayList<String> nextStates = new ArrayList<String>();
		int i,pos=-1,newPos=0;
		for(i=0;i<state.length();i++)
		{
			if(state.charAt(i)==' ')
			{
				pos=i;
				break;
			}
		}
		boolean up=false,down=false,left=false,right=false;
		switch (pos) {
		case 0:
			right=true;
			down=true;
			break;
		case 1:
			left=true;
			right=true;
			down=true;
			break;
		case 2:
			left=true;
			down=true;
			break;
		case 3:
			right=true;
			down=true;
			up=true;
			break;
		case 4:
			left=true;
			right=true;
			down=true;
			up=true;
			break;
		case 5:
			left=true;
			down=true;
			up=true;
			break;
		case 6:
			right=true;
			up=true;
			break;
		case 7:
			left=true;
			right=true;
			up=true;
			break;
		case 8:
			left=true;
			up=true;
			break;
		}
		if(up==true)
		{
			newPos=pos-3;
			char a=state.charAt(newPos);
			nextStates.add(state.replace(a,'*').replace(' ', a).replace('*',' '));
		}
		if(down==true)
		{
			newPos=pos+3;
			char a=state.charAt(newPos);
			nextStates.add(state.replace(a,'*').replace(' ', a).replace('*',' '));
		}
		if(right==true)
		{
			newPos=pos+1;
			char a=state.charAt(newPos);
			nextStates.add(state.replace(a,'*').replace(' ', a).replace('*',' '));
		}
		if(left==true)
		{
			newPos=pos-1;
			char a=state.charAt(newPos);
			nextStates.add(state.replace(a,'*').replace(' ', a).replace('*',' '));
		}
		return nextStates;
	}
	
	public boolean isFinal(String state)
	{
		if(state.compareTo(goalString)==0)
		{
			return true;
		}
		return false;
	}
}

package entities;

import java.util.ArrayList;

public class HashtagsGraph {

	private ArrayList<EdgeObj> edgs;
	
	public void addEdge(EdgeObj edg)
	{
		for(EdgeObj ed : edgs)
		{
			if(edg.equals(ed))
			{
				ed.increamentWeight();
				return;
			}
		}
		edgs.add(edg);
	}

	public HashtagsGraph()
	{
		edgs = new ArrayList<EdgeObj>();
	}
}

package source;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.tomtom2.objectgrapher.store.Graph;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "/home/thomas/master/memoire/workspace/ObjectGrapher/src/source/Solver.java";
		Graph g = new Graph(url);
		//System.out.println(g.methodsMap.keySet());
		g.makeBasicGraph(0);
		
		String str = " this.attr= \"mouarf!\";";
		Pattern p = Pattern.compile(".*?this.?(\\w+)=.*;");
		Matcher m = p.matcher(str);
		m.find();
		System.out.println(m.group(1));
		
		

	}
}


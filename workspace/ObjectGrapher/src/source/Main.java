package source;

import com.github.tomtom2.objectgrapher.store.Graph;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "/home/thomas/master/memoire/workspace/ObjectGrapher/src/source/Solver.java";
		Graph g = new Graph(url);
		System.out.println(g.methodsMap.keySet());
		g.makeBasicGraph(0);

	}
}

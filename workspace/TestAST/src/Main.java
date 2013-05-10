import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = "/home/thomas/master/memoire/workspace/ObjectGrapher/src/source/Solver.java";
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String source = "";
		int c;
		try {
			while((c=in.read())!=-1){
				source += (char)c;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		//parser.setSource("public class A { int i = 9;  \n int j; \n ArrayList<Integer> al = new ArrayList<Integer>();j=1000; if(j>i){al.add(j);}}".toCharArray());
		//parser.setSource("/*abc*/".toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		//ASTNode node = parser.createAST(null);
 
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new Visitor());
		/*cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
 
			public boolean visit(VariableDeclarationFragment node) {
				System.out.println("Declaration of '"+node+"' at line"+cu.getLineNumber(node.getStartPosition()));
				return false; // do not continue to avoid usage info
			}
 
			public boolean visit(MethodDeclaration node) {
				System.out.println("MethodDeclaration '"+ node.getName() + "' at line " +	cu.getLineNumber(node.getStartPosition()));
				Object act = null;
				node.accept(new ASTVisitor() {
					public boolean visit(VariableDeclarationFragment node) {
						System.out.println("Sub Declaration of '"+node+"' at line"+cu.getLineNumber(node.getStartPosition()));
						return false; // do not continue to avoid usage info
					}
					public boolean visit(VariableDeclaration node) {
						System.out.println("Sub VariableDeclaration '"+node+"' at line"+cu.getLineNumber(node.getStartPosition()));
						return false; // do not continue to avoid usage info
					}
				});
				System.out.println(node.parameters());
				
				return true;
			}
//			
//			public boolean visit(org.eclipse.jdt.core.dom.ConditionalExpression node) {
//				System.out.println("Conditional Exp: '" + node + "' at line " +	cu.getLineNumber(node.getStartPosition()));
//				return true;
//			}
			
			public boolean visit(VariableDeclaration node) {
				System.out.println("VariableDeclaration: '" + node + "' of type " +	node.getNodeType());
				return true;
			}
			public boolean visit(VariableDeclarationExpression node) {
				System.out.println("VariableDeclarationExpression: '" + node + "' of type " +	node.getNodeType());
				return true;
			}
			public boolean visit(VariableDeclarationStatement node) {
				System.out.println("VariableDeclaration: '" + node + "' of type " +	node.getNodeType());
				return true;
			}

			
		});
		*/
	}
}

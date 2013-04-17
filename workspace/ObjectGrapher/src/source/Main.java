package source;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "/home/thomas/master/memoire/workspace/ObjectGrapher/";
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(url + "src/source/Solver.java");

		CompilationUnit cu;
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		
		System.out.println(cu.toString());
		String classCore = cu.toString();
		changeMethods(cu);
		//new MethodVisitor().visit(cu, null);

		// prints the resulting compilation unit to default system output
		
		System.out.println("\n\n\n\n\n\n\n");
		//System.out.println(cu);
		// visit and print the methods names
		

	}
	
	private static void changeMethods(CompilationUnit cu) {
        List<TypeDeclaration> types = cu.getTypes();
        for (TypeDeclaration type : types) {
        	System.out.println("type : "+type);
            List<BodyDeclaration> members = type.getMembers();
            for (BodyDeclaration member : members) {
            	System.out.println("member : "+member);
                if (member instanceof MethodDeclaration) {
                	System.out.println(">>> methode");
                    MethodDeclaration method = (MethodDeclaration) member;
                    //changeMethod(method);
                }
                else if(member instanceof japa.parser.ast.body.ConstructorDeclaration){
                	System.out.println(">>> constructor");
                }
                else if(member instanceof japa.parser.ast.body.FieldDeclaration){
                	System.out.println(">>> field");
                }
            }
        }
    }

    private static void changeMethod(MethodDeclaration n) {
        // change the name of the method to upper case
        n.setName(n.getName().toUpperCase());

        // create the new parameter
        Parameter newArg = ASTHelper.createParameter(ASTHelper.INT_TYPE, "value");

        // add the parameter to the method
        ASTHelper.addParameter(n, newArg);
    }


        
	/**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
            //System.out.println(n.getName());
        	n.setBody(null);
            System.out.println(n.getClass());
            System.out.println("\n");
        }
    }


}

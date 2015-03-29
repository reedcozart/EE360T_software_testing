package pset3;


import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.MethodGen;
public class GraphGenerator {
	public CFG createCFG(String className) throws ClassNotFoundException {
		CFG cfg = new CFG();
		JavaClass jc = Repository.lookupClass(className);
		ClassGen cg = new ClassGen(jc);
		ConstantPoolGen cpg = cg.getConstantPool();
		for (Method m: cg.getMethods()) {
			cfg.addNode(-1, m, jc); //create the exit node
			MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
			InstructionList il = mg.getInstructionList();
			InstructionHandle[] handles = il.getInstructionHandles();
			for (InstructionHandle ih: handles) {
				int position = ih.getPosition();
				cfg.addNode(position, m, jc);
				Instruction inst = ih.getInstruction();
				//System.out.print(ih.toString() + " ** ");
				//System.out.println(inst.toString());
				// your code goes here
				String name = inst.getName();
				InstructionHandle nextInstHandle = ih.getNext();
				if(name.contains("return")){ //TODO terminating node?
					//System.out.println(inst.getName());
					cfg.addEdge(position, -1, m, jc);
				}else if(name.contains("load")||name.contains("invokespecial")){
					cfg.addEdge(position, nextInstHandle.getPosition(), m, jc);
				}else if(name.contains("if")){
					BranchInstruction bi = (BranchInstruction) inst;
					cfg.addEdge(position, bi.getTarget().getPosition(), m, jc);
					cfg.addEdge(position, nextInstHandle.getPosition(), m, jc);
				}
			} 
		}
		return cfg; 
	}
	public CFG createCFGWithMethodInvocation(String className) throws ClassNotFoundException {
		CFG cfg = new CFG();
		JavaClass jc = Repository.lookupClass(className);
		ClassGen cg = new ClassGen(jc);
		ConstantPoolGen cpg = cg.getConstantPool();
		for (Method m: cg.getMethods()) {
			cfg.addNode(-1, m, jc); //create the exit node
			MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
			InstructionList il = mg.getInstructionList();
			InstructionHandle[] handles = il.getInstructionHandles();
			for (InstructionHandle ih: handles) {
				int position = ih.getPosition();
				cfg.addNode(position, m, jc);
				Instruction inst = ih.getInstruction();
				System.out.print(ih.toString() + " ** ");
				System.out.println(inst.toString() + " * "+ m);
				// your code goes here
				String name = inst.getName();
				InstructionHandle nextInstHandle = ih.getNext();
				if(name.contains("return")){ //TODO terminating node?
					//System.out.println(inst.getName());
					cfg.addEdge(-1, m, jc, -1, m, jc);
				}else if(name.contains("load")||name.contains("invokespecial")){
					cfg.addEdge(position, nextInstHandle.getPosition(), m, jc);
				}else if(name.contains("if")){
					BranchInstruction bi = (BranchInstruction) inst;
					cfg.addEdge(position, bi.getTarget().getPosition(), m, jc);
					cfg.addEdge(position, nextInstHandle.getPosition(), m, jc);
				}else if(name.toLowerCase().contains("invokestatic")){
					InvokeInstruction ii = (InvokeInstruction) inst;
					//Method otherMethod = cg.containsMethod(ii.getMethodName(cpg), ii.getSignature(cpg));
					JavaClass nextJc = Repository.lookupClass(ii.getClassName(cpg));
					Method nextMethod = cg.containsMethod(ii.getName(cpg), ii.getSignature(cpg));
					cfg.addEdge(position, m, jc, 0, nextMethod, nextJc); //outgoing edge
					cfg.addEdge(-1, nextMethod, nextJc, nextInstHandle.getPosition(), m, jc); //incoming edge
				}
			} 
		}
		return cfg; 
	}
	public static void main(String[] a) throws ClassNotFoundException {
		GraphGenerator gg = new GraphGenerator();
		gg.createCFG("pset3.C"); // example invocation of createCFG
		gg.createCFGWithMethodInvocation("pset3.D"); // example invocation of createCFGWithMethodInovcation
		CFG searchThisGraph = gg.createCFGWithMethodInvocation("pset3.D");
		boolean shouldBeTrue = searchThisGraph.isReachable("main", "pset3.D", "bar", "pset3.D");
		if(shouldBeTrue){ 
			System.out.println("ShouldBeTrue is True!");
		} else System.out.println("shouldBeTrue is false! Shenanigans!");
		boolean shouldBeFalse = searchThisGraph.isReachable("main", "pset3.D", "barb", "pset3.D");
		if(!shouldBeFalse){
			System.out.println("ShouldBeFalse is False! YAY!");
		}
		
	} 
}
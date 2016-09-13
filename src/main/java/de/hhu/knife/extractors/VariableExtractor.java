package de.hhu.knife.extractors;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VariableExtractor {
	private static final Logger logger = LoggerFactory.getLogger(Extractor.class);

	public static List<String> getVariables(String codeBlock) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("public class DummyWrap\n");
		stringBuilder.append("{\n");
		stringBuilder.append(codeBlock);
		stringBuilder.append("}\n");
		try {
			CompilationUnit cu = JavaParser.parse(new StringReader(stringBuilder.toString()));
			LocalVariableCollector localVarialbeCollector = new LocalVariableCollector();
			localVarialbeCollector.visit(cu, null);
			return localVarialbeCollector.getVariables();

		} catch (final ParseException e) {
			logger.error(String.format("Failed extracting from method with the following code: %s", codeBlock), e);
		}
		return Collections.emptyList();

	}

	private static class LocalVariableCollector extends VoidVisitorAdapter {
		private final List<String> variables;

		public LocalVariableCollector() {
			this.variables = new ArrayList<String>();
		}

		public List<String> getVariables() {
			return variables;
		}

		@Override
		public void visit(VariableDeclarationExpr n, Object arg) {
			List<VariableDeclarator> myVars = n.getVars();
			for (VariableDeclarator vars : myVars) {
				this.variables.add(vars.getId().getName());
			}
		}
	}
}

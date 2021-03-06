package com.cognifide.aemrules.checks;

import com.cognifide.aemrules.tag.Tags;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.List;
import org.sonar.java.tag.Tag;

@Rule(
	key = InjectorTryWithResourcesCheck.RULE_KEY,
	name = InjectorTryWithResourcesCheck.RULE_MESSAGE,
	priority = Priority.INFO,
	tags = {Tag.JAVA_7, Tags.AEM}
)
public class InjectorTryWithResourcesCheck extends InjectorShouldBeClosedCheck {

	public static final String RULE_KEY = "AEM-5";

	public static final String RULE_MESSAGE = "Injector can be closed using try-with-resources Java 7 feature.";

	@Override
	public void visitMethod(MethodTree method) {
		List<VariableTree> injectors = findInjectorsInMethod(method);
		for (VariableTree injector : injectors) {
			boolean closed = checkIfInjectorIsClosedInMethod(method, injector);
			if (closed) {
				context.reportIssue(this, method, RULE_MESSAGE);
			}
		}
		super.visitMethod(method);
	}

}

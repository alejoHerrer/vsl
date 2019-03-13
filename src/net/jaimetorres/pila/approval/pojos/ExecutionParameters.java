package net.jaimetorres.pila.approval.pojos;

import net.jaimetorres.pila.approval.core.singletons.ApprovalActivosFileParser;

public class ExecutionParameters {

	private String[] args;

	// SINGLETON
	private static ExecutionParameters instance;

	private ExecutionParameters(String[] args) {
		this.args = args;
	}

	public static ExecutionParameters getInstance(String[] args) {

		if (instance == null) {
			synchronized (ExecutionParameters.class) {
				if (instance == null) {
					instance = new ExecutionParameters(args);
				}
			}
		}

		return instance;
	}

	// end singleton
	public String[] getArgs() {
		return args;
	}
}

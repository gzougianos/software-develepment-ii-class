package gr.uoi.cs.model.strategies;

import java.util.HashMap;

public class VersionsStrategyFactory {
	private HashMap<String, VersionsStrategy> strategies;

	public VersionsStrategyFactory() {
		strategies = new HashMap<String, VersionsStrategy>();
		strategies.put(VersionsStrategy.VOLATILE, new VolatileVersionsStrategy());
		strategies.put(VersionsStrategy.STABLE, new StableVersionsStrategy());
	}

	public VersionsStrategy createStrategy(String strategyId) {
		return strategies.get(strategyId);
	}
}

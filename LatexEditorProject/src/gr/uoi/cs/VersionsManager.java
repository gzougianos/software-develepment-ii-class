package gr.uoi.cs;

import model.Document;
import model.strategies.VersionsStrategy;
import model.strategies.VersionsStrategyFactory;

public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy strategy;
	private VersionsStrategyFactory strategyFactory;

	public VersionsManager(VersionsStrategyFactory strategyFactory) {
		this.strategyFactory = strategyFactory;
		strategy = strategyFactory.createStrategy(VersionsStrategy.VOLATILE);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public void changeStrategy(String strategyId) {
		VersionsStrategy newStrategy = strategyFactory.createStrategy(strategyId);
		newStrategy.setEntireHistory(strategy.getEntireHistory());
		strategy = newStrategy;
		enable();
	}

	public void putVersion(Document document) {
		strategy.putVersion(document);
	}

	public void rollback() {
		// TODO Auto-generated method stub
//		if (isEnabled() == false) {
//			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
//		} else {
//			Document doc = strategy.getVersion();
//			if (doc == null) {
//				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
//			} else {
//				strategy.removeVersion();
//				latexEditorView.setCurrentDocument(doc);
//			}
//		}

	}

}

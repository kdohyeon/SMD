package org.prom5.mining.dot;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.prom5.framework.log.LogReader;
import org.prom5.framework.models.ModelGraphPanel;
import org.prom5.framework.models.dot.DotModel;
import org.prom5.mining.MiningResult;
/**
 * <p>Title: DotResult</p>
 *
 * <p>Description: Contains a dot model.</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: TU/e</p>
 *
 * @author Eric Verbeek
 * @version 1.0
 */
public class DotResult implements MiningResult {

	protected DotModel model;
	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JScrollPane netContainer = new JScrollPane();

	/**
	 * Create a DotResult from some Dot model.
	 * @param model DotModel The given Dot model.
	 */
	public DotResult(DotModel model) {
		this.model = model;
		ModelGraphPanel gp = model.getGrappaVisualization();
		netContainer.setViewportView(gp);
		netContainer.invalidate();
		netContainer.repaint();
	}

	/**
	 * No log reader, sorry...
	 * @return LogReader
	 */
	public LogReader getLogReader() {
		return null;
	}

	/**
	 * Simple thing. Nothing fancy.
	 * @return JComponent
	 */
	public JComponent getVisualization() {
		mainPanel.add(netContainer, BorderLayout.CENTER);
		return mainPanel;
	}
}

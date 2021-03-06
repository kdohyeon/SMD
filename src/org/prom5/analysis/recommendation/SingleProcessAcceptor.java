package org.prom5.analysis.recommendation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.prom5.analysis.AnalysisInputItem;
import org.prom5.analysis.AnalysisPlugin;
import org.prom5.framework.log.LogFile;
import org.prom5.framework.log.LogReader;
import org.prom5.framework.log.filter.DefaultLogFilter;
import org.prom5.framework.log.rfb.BufferedLogReader;
import org.prom5.framework.plugin.ProvidedObject;
import org.prom5.framework.ui.MainUI;
import org.prom5.framework.ui.Utils;
import org.prom5.framework.ui.filters.GenericFileFilter;
import org.prom5.framework.util.CenterOnScreen;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
abstract public class SingleProcessAcceptor implements AnalysisPlugin {

    public AnalysisInputItem[] getInputItems() {
        AnalysisInputItem[] items = {
                                    new AnalysisInputItem("Optional log file", 0, 1) {
            public boolean accepts(ProvidedObject object) {
                Object[] o = object.getObjects();
                boolean hasLog = false;

                for (int i = 0; i < o.length; i++) {
                    if (o[i] instanceof BufferedLogReader) {
                        hasLog = true;
                    }
                }
                return hasLog || true;
            }
        }
        } ;
        return items;
    }

    public boolean isOneProcess(LogReader log) {
        return log.getLogSummary().getProcesses().length == 1;
    }

    private void showWarningDialog(String message) {
        final JDialog dialog = new JDialog(MainUI.getInstance(), "Warning about selected log file:", true);

        JLabel argLabel = new JLabel(message);

        JButton okButton = new JButton("    Ok    ");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        dialog.getContentPane().setLayout(new GridBagLayout());

        dialog.getContentPane().add(argLabel,
                                    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        dialog.getContentPane().add(okButton,
                                    new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 5, 5, 5), 0, 0));

        dialog.pack();
        CenterOnScreen.center(dialog);
        dialog.setVisible(true);

    }

    public JComponent analyse(AnalysisInputItem[] inputs) {
        LogReader log = null;
        boolean startServerImmediately = false;
        Object[] o;
        if (inputs == null || inputs[0] == null || inputs[0].getProvidedObjects() == null) {
            o = new Object[0];
        } else {
            o = (inputs[0].getProvidedObjects())[0].getObjects();
        }
        for (int i = 0; i < o.length; i++) {
            if (o[i] instanceof BufferedLogReader) {
                log = (BufferedLogReader) o[i];
            }
            if (o[i].equals(MainUI.STARTEDFROMCOMMANDLINE)) {
                startServerImmediately = true;
            }
        }
        if (log == null) {
            // no log was provided
            String filename = getFileName();
            try {
                log = instantiateEmptyLog(LogFile.instantiateEmptyLogFile(filename));
            } catch (IOException ex) {
                return null;
            }
        }
        if (!(log instanceof BufferedLogReader)) {
            showWarningDialog("<html>This plugin can only be used on readable logs.<br>" +
                              " Please select a different Log Reader mechanism.</html>");
            return null;
        }

        if (!isOneProcess(log)) {
            showWarningDialog("<html>This log contains more than one process.<br>" +
                              "Please select a log with a single process.</html>");
            return null;
        }
        return analyse((BufferedLogReader)log, startServerImmediately);
    }


    private LogReader instantiateEmptyLog(LogFile file) {

        LogReader log = null;
        // Create the underlying file.
        try {
            log = (BufferedLogReader) BufferedLogReader.createInstance(new DefaultLogFilter(
                    DefaultLogFilter.INCLUDE), file);
        } catch (Exception ex) {
            // For some reason, the file could not be instantiated;
            ex.printStackTrace();
            return null;
        }
        return log;
    }

    private String getFileName() {
        final String filename = Utils.saveFileDialog(MainUI.getInstance().getDesktop(),
                new GenericFileFilter(".mxml"));
        return filename;

    }

    protected abstract JComponent analyse(BufferedLogReader log, boolean startServerImmediately);
}

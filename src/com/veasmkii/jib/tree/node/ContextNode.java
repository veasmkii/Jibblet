package com.veasmkii.jib.tree.node;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.veasmkii.jib.gui.desktop.JibWindow;

public abstract class ContextNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 870602535078709343L;

	protected JPopupMenu menu;

	public abstract JPopupMenu getMenu();

	public abstract JibWindow getWindow();

	@Override
	public abstract String toString();

	public abstract void quitting();
}

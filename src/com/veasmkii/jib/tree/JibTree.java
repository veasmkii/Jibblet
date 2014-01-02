package com.veasmkii.jib.tree;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.veasmkii.jib.gui.desktop.JibDesktop;
import com.veasmkii.jib.tree.node.ContextNode;
import com.veasmkii.jib.tree.node.Root;
import com.veasmkii.jib.tree.renderer.JibTreeRenderer;

public final class JibTree extends JTree
{

	private static final long serialVersionUID = -7991593628626849292L;

	private final Root root;
	private final JibDesktop desktop;
	public static DefaultTreeModel treeModel;

	public JibTree( final JibDesktop desktop )
	{
		this.desktop = desktop;
		this.root = new Root( desktop, "Servers" );

		setupModel();
		createTree();
		
		setMinimumSize( new Dimension( 200, getSize().height ) );

	}

	private void setupModel()
	{
		treeModel = new DefaultTreeModel( root );
	}

	private void createTree()
	{
		setModel( treeModel );

		setExpandsSelectedPaths( true );

		setCellRenderer( new JibTreeRenderer() );

		addTreeSelectionListener( new TreeSelectionListener()
		{
			@Override
			public void valueChanged( TreeSelectionEvent e )
			{
				System.out.println( "Selection Change" );
				showWindow();
			}
		} );

		addMouseListener( new MouseAdapter()
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				if ( e.getClickCount() > 1 )
				{
					showWindow();
				}

				if ( e.getButton() == MouseEvent.BUTTON3 )
				{
					final int x = e.getX(), y = e.getY();

					// Force tree selection
					JibTree.this.setSelectionPath( JibTree.this.getPathForLocation( x, y ) );

					showNodeMenu( x, y );
				}
			}
		} );
	}

	private ContextNode getLastNode()
	{
		final DefaultMutableTreeNode node = (DefaultMutableTreeNode) JibTree.this
				.getLastSelectedPathComponent();

		if ( node instanceof ContextNode )
		{
			return (ContextNode) node;
		}
		return null;
	}

	private void showNodeMenu( int x, int y )
	{
		final ContextNode lastNode = getLastNode();

		if ( lastNode != null )
		{
			lastNode.getMenu().show( this, x, y );
		}
	}

	private void showWindow()
	{
		final ContextNode lastNode = getLastNode();

		if ( lastNode != null )
		{
			lastNode.getWindow().show();
		}
	}

	public void quitting()
	{
		root.quitting();
	}

}

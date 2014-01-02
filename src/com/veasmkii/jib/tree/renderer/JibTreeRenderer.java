package com.veasmkii.jib.tree.renderer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.veasmkii.jib.tree.node.Channel;
import com.veasmkii.jib.tree.node.Root;
import com.veasmkii.jib.tree.node.Server;
import com.veasmkii.jib.tree.node.User;
import com.veasmkii.jib.utils.Images;

public class JibTreeRenderer extends DefaultTreeCellRenderer
{
	private static final long serialVersionUID = -3832817137066529941L;

	private ImageIcon rootIcon;
	private ImageIcon serverIcon;
	private ImageIcon channelIcon;
	private ImageIcon userIcon;

	public JibTreeRenderer()
	{
		rootIcon = new ImageIcon( Images.ROOT.getImage() );
		serverIcon = new ImageIcon( Images.SERVER.getImage() );
		channelIcon = new ImageIcon( Images.CHANNEL.getImage() );
		userIcon = new ImageIcon( Images.USER.getImage() );
	}

	@Override
	public Component getTreeCellRendererComponent( JTree tree, Object value, boolean sel,
			boolean expanded, boolean leaf, int row, boolean hasFocus )
	{
		final Component c = super.getTreeCellRendererComponent( tree, value, sel, expanded, leaf,
				row, hasFocus );

		if ( c instanceof JLabel )
		{
			final JLabel cell = (JLabel) c;

			if ( value instanceof Root )
			{
				cell.setIcon( rootIcon );
				cell.setText( ( (Root) value ).toString() );
			}
			else if ( value instanceof Server )
			{
				cell.setIcon( serverIcon );
				cell.setText( ( (Server) value ).toString() );
			}
			else if ( value instanceof Channel )
			{
				cell.setIcon( channelIcon );
			}
			else if ( value instanceof User )
			{
				cell.setIcon( userIcon );
			}
		}

		return c;
	}

}

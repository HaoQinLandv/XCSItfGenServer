package com.foxhis.itf.main;

import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;

public class JTrayIcon extends TrayIcon
{
	private JDialog dialog;
	private JPopupMenu popup;

	public JTrayIcon(Image image)
	{
		super(image);
	}

	public JTrayIcon(Image image, String tooltip)
	{
		super(image, tooltip);
	}

	public JTrayIcon(Image image, String tooltip, PopupMenu popup)
	{
		super(image, tooltip, popup);
	}

	public JTrayIcon(Image image, String tooltip, JPopupMenu popupMenu)
	{
		super(image, tooltip, null);
		this.popup = popupMenu;
		this.dialog = new JDialog();
		this.dialog.setSize(0, 0);
		this.dialog.setUndecorated(true);
//		AWTUtilities.setWindowOpaque(this.dialog, false);

		this.dialog.addWindowFocusListener(new WindowFocusListener()
		{
			public void windowLostFocus(WindowEvent e) {
				JTrayIcon.this.dialog.setVisible(false);
			}

			public void windowGainedFocus(WindowEvent e)
			{
			}
		});
		addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				if (e.getButton() == 3) {
					JTrayIcon.this.popup.setLocation(e.getX(), e.getY() - JTrayIcon.this.popup.getHeight());
					JTrayIcon.this.dialog.setLocation(e.getX(), e.getY() - JTrayIcon.this.popup.getHeight());
					JTrayIcon.this.popup.setInvoker(JTrayIcon.this.dialog);
					JTrayIcon.this.dialog.setVisible(true);
					JTrayIcon.this.popup.setVisible(true);
				}
			}
		});
	}
}

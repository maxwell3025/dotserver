package dots;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class client extends JPanel implements MouseListener, Runnable {
	JFrame frame = new JFrame();
	Socket connection;
	BufferedOutputStream out;
	BufferedInputStream in;
	BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
	byte[] shades = new byte[65536];

	public client() {
		setPreferredSize(new Dimension(256, 256));
		frame.add(this);
		frame.addMouseListener(this);
		addMouseListener(this);
		frame.pack();
		frame.setDefaultCloseOperation(3);
		new Thread(this).start();
		String IP = JOptionPane.showInputDialog("what's the IP?");
		int Port = Integer.parseInt(JOptionPane.showInputDialog("what's the IP?"));
		connection = new Socket();
		try {
			connection = new Socket(IP, Port);
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
		try {
			out = new BufferedOutputStream(connection.getOutputStream());
			in = new BufferedInputStream(connection.getInputStream());
		} catch (IOException e) {
		}
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		client c = new client();
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		byte x = (byte) e.getX();
		byte y = (byte) e.getY();
		Random r = new Random();
		byte[] b = {x,y,(byte)r.nextInt()};
		try {
			out.write(b);
		} catch (IOException e1) {
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public void update() {
		
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			repaint();
			update();
		}
	}

}

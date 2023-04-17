import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
public class Ventana extends JFrame{
	
	ArrayList<JButton> botones = new ArrayList<JButton>();
	
	public Ventana() {
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		jp.setSize(500,500);
		jp.setBackground(Color.decode("#C8B7FF"));
		jp.setLayout(null);
		this.add(jp);
		Random rand = new Random();
		
		Ventana n = this;
		
		jp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				n.setFocusable(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int numAleatorio = rand.nextInt(256*256*256);
		        String hexRandom = Integer.toHexString(numAleatorio);
		        
				
		        JButton botonRandom = new JButton(hexRandom);
				botonRandom.setSize(rand.nextInt(100),rand.nextInt(100));
				botonRandom.setLocation(e.getX(), e.getY());
				botonRandom.setOpaque(true);
				botonRandom.setBackground(Color.decode("#"+hexRandom)); 
				
				botones.add(botonRandom);
				
				jp.add(botonRandom);
				botonRandom.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						jp.remove(botonRandom);
						
						n.requestFocus(true);
					}
					
				});
				jp.repaint();
				jp.revalidate();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				n.requestFocus(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				int numAleatorio = rand.nextInt(256*256*256);
		        String hexRandom = Integer.toHexString(numAleatorio);
				jp.setBackground(Color.decode("#"+hexRandom)); 
				n.requestFocus(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyCode());
				if(e.getKeyCode()==32) {
					botones.clear();
					jp.removeAll();
					
				}else if(e.getKeyCode()==87) {
					for (JButton jButton : botones) {
						jButton.setLocation(jButton.getX(), (jButton.getY()-10));
					}
				}else if(e.getKeyCode()==65) {
					for (JButton jButton : botones) {
						jButton.setLocation((jButton.getX()-10), (jButton.getY()));
					}
				}else if(e.getKeyCode()==68) {
					for (JButton jButton : botones) {
						jButton.setLocation((jButton.getX()+10), (jButton.getY()));
					}
				}else if(e.getKeyCode()==83) {
					for (JButton jButton : botones) {
						jButton.setLocation((jButton.getX()), (jButton.getY()+10));
					}
				}
				n.repaint();
				n.revalidate();
		}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
}

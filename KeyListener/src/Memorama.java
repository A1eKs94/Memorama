import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

public class Memorama {

	private JFrame frame;
	// Creo arraylist para almacenar los botones
	private ArrayList<JButton> valor = new ArrayList<JButton>();
	private HashMap<JButton, Icon> cartas = new HashMap<JButton, Icon>();
	private JButton[] match = new JButton[2];
	private int cont = 0;
	private ImageIcon icono5 = new ImageIcon("5.jpg");
	private int puntos = 0;
	private int tiempo = 0;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Memorama window = new Memorama();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Memorama() {
		initialize();
		frame.setResizable(false);
		Thread juego = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.print("");
				while(Thread.currentThread().isAlive()) {
					System.out.print("");
					if(cont >= 2) {
						System.out.println("comprobando");
						if(isMatch(match[0].getIcon(), match[1].getIcon())) {
							JOptionPane.showMessageDialog(null, "Match");
							match[0] = null;
							match[1] = null;
							cont = 0;
							puntos++;
							if(puntos>=4) {
								JOptionPane.showMessageDialog(null,"Felicidades!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "No Match");
							match[0].setEnabled(true);
							match[1].setEnabled(true);
							match[0].setIcon(icono5);
							match[1].setIcon(icono5);
							match[0] = null;
							match[1] = null;
							cont = 0;
						}
					}
				}
			}
			
		});
		juego.start();
	}
	
	public boolean isMatch(Icon image1, Icon image2) {
        ImageIcon icono1 = (ImageIcon) image1;
        ImageIcon icono2 = (ImageIcon) image2;

        BufferedImage imagen1 = new BufferedImage(icono1.getIconWidth(), icono1.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        imagen1.getGraphics().drawImage(icono1.getImage(), 0, 0, null);
        BufferedImage imagen2 = new BufferedImage(icono2.getIconWidth(), icono2.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        imagen2.getGraphics().drawImage(icono2.getImage(), 0, 0, null);

        if (imagen1.getWidth() == imagen2.getWidth() && imagen1.getHeight() == imagen2.getHeight()) {
            for (int y = 0; y < imagen1.getHeight(); y++) {
                for (int x = 0; x < imagen1.getWidth(); x++) {
                    if (imagen1.getRGB(x, y) != imagen2.getRGB(x, y)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
	
	
	// Añado valor a los botones
	private void reiniciar(){
		int x = 0;
		Collections.shuffle(valor);
		for (JButton jButton : valor) {
			x++;
			jButton.setText(String.valueOf(x));
		}
	}
	
	private void palo() {
		 ImageIcon icono = new ImageIcon("1.jpg");
		 ImageIcon icono2 = new ImageIcon("2.jpg");
		 ImageIcon icono3 = new ImageIcon("3.jpg");
		 ImageIcon icono4 = new ImageIcon("4.jpg");
		for (JButton jButton : this.valor) {
			switch(jButton.getText()) {
			case "1":
				cartas.put(jButton, icono);
				break;
			case "2":
				cartas.put(jButton, icono);
				break;
			case "3":
				cartas.put(jButton, icono2);
				break;
			case "4":
				cartas.put(jButton, icono2);
				break;
			case "5":
				cartas.put(jButton, icono3);
				break;
			case "6":
				cartas.put(jButton, icono3);
				break;
			case "7":
				cartas.put(jButton, icono4);
				break;
			case "8":
				cartas.put(jButton, icono4);
				break;
			}
		}
	}
	
	private void bocaAbajo() {
		
		for (JButton jButton : valor) {
			jButton.setIcon(icono5);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(179, 222, 255));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		panel.add(lblNewLabel);
		
		Timer timer = new Timer(1000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        tiempo++;
		        int segundos = tiempo % 60;
		        String cronometro = String.format("%02d", segundos);
		        lblNewLabel.setText(cronometro);
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 255));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 4, 0, 0));
		
		// Crear botones y añadirlos al array
		
		JButton btnNewButton_2 = new JButton();
		panel_1.add(btnNewButton_2);
		valor.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_2.getIcon(),icono5)) {
					btnNewButton_2.setIcon(cartas.get(btnNewButton_2));
					match[cont] = btnNewButton_2;
					cont++;
				}
			}
			
		});
		
		JButton btnNewButton_3 = new JButton();
		panel_1.add(btnNewButton_3);
		valor.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_3.getIcon(),icono5)) {
					btnNewButton_3.setIcon(cartas.get(btnNewButton_3));
					match[cont] = btnNewButton_3;
					cont++;
				}
				
			}
			
		});
		
		JButton btnNewButton_5 = new JButton();
		panel_1.add(btnNewButton_5);
		valor.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_5.getIcon(),icono5)) {
					btnNewButton_5.setIcon(cartas.get(btnNewButton_5));
					match[cont] = btnNewButton_5;
					cont++;
				}
				
			}
			
		});
		
		JButton btnNewButton_6 = new JButton();
		panel_1.add(btnNewButton_6);
		valor.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_6.getIcon(),icono5)) {
				btnNewButton_6.setIcon(cartas.get(btnNewButton_6));
				match[cont] = btnNewButton_6;
				cont++;
				}
			}
			
		});
		
		JButton btnNewButton_4 = new JButton();
		panel_1.add(btnNewButton_4);
		valor.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_4.getIcon(),icono5)) {
				btnNewButton_4.setIcon(cartas.get(btnNewButton_4));
				match[cont] = btnNewButton_4;
				cont++;
				}
			}
			
		});
		
		JButton btnNewButton_7 = new JButton();
		panel_1.add(btnNewButton_7);
		valor.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_7.getIcon(),icono5)) {
				btnNewButton_7.setIcon(cartas.get(btnNewButton_7));
				match[cont] = btnNewButton_7;
				cont++;
				}
			}
			
		});
		
		JButton btnNewButton_1 = new JButton();
		panel_1.add(btnNewButton_1);
		valor.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_1.getIcon(),icono5)) {
				btnNewButton_1.setIcon(cartas.get(btnNewButton_1));
				match[cont] = btnNewButton_1;
				cont++;
				}
			}
			
		});
		
		JButton btnNewButton_8 = new JButton();
		panel_1.add(btnNewButton_8);
		valor.add(btnNewButton_8);
		btnNewButton_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isMatch(btnNewButton_8.getIcon(),icono5)) {
				btnNewButton_8.setIcon(cartas.get(btnNewButton_8));
				match[cont] = btnNewButton_8;
				cont++;
				}
			}
			
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 128));
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Reiniciar");
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
				palo();
				bocaAbajo();
				tiempo = 0;
				timer.start();
			}
		});
		panel_2.add(btnNewButton);
	}

}

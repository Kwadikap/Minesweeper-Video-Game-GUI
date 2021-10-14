
//LINK TO YOUTUBE VIDEO:https://youtu.be/CsHyL86fMdk
//MineSweeper Gui Code:
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;

public class MineSweeper extends JFrame {
	Grid grid;
	private int lastRow, lastCol;
	private int r, c;
	private static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
	int row, col, moves, maxMoves, numBombs, flagsRemaining;
	JButton[][] buttons;
	JPanel gamePanel;
	JPanel titlePanel;
	JLabel titleLabel, flagLabel, flagPicLabel;
	String titlePanelString, flags;
	ImageIcon[] icons;

	public MineSweeper() {
		super("MineSweeper");
		grid = new Grid();
		gamePanel = new JPanel();
		titlePanel = new JPanel();
		icons = new ImageIcon[9];

		ImageIcon zeroImg = new ImageIcon(getClass().getResource("0.png"));
		ImageIcon zeroImgScaled = new ImageIcon(zeroImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[0] = zeroImgScaled;

		ImageIcon oneImg = new ImageIcon(getClass().getResource("1.png"));
		ImageIcon oneImgScaled = new ImageIcon(oneImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[1] = oneImgScaled;

		ImageIcon twoImg = new ImageIcon(getClass().getResource("2.png"));
		ImageIcon twoImgScaled = new ImageIcon(twoImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[2] = twoImgScaled;

		ImageIcon threeImg = new ImageIcon(getClass().getResource("3.png"));
		ImageIcon threeImgScaled = new ImageIcon(threeImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[3] = threeImgScaled;

		ImageIcon fourImg = new ImageIcon(getClass().getResource("4.png"));
		ImageIcon fourImgScaled = new ImageIcon(fourImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[4] = fourImgScaled;

		ImageIcon fiveImg = new ImageIcon(getClass().getResource("5.png"));
		ImageIcon fiveImgScaled = new ImageIcon(fiveImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[5] = fiveImgScaled;

		ImageIcon sixImg = new ImageIcon(getClass().getResource("6.png"));
		ImageIcon sixImgScaled = new ImageIcon(sixImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[6] = sixImgScaled;

		ImageIcon sevenImg = new ImageIcon(getClass().getResource("7.png"));
		ImageIcon sevenImgScaled = new ImageIcon(sevenImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[7] = sevenImgScaled;

		ImageIcon eightImg = new ImageIcon(getClass().getResource("8.png"));
		ImageIcon eightImgScaled = new ImageIcon(eightImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		icons[8] = eightImgScaled;

		buildGamePanel();
		buildTitlePanel();
		add(gamePanel, BorderLayout.CENTER);
		add(titlePanel, BorderLayout.NORTH);

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void buildGamePanel() {
		lastRow = grid.getNumRows() - 1;
		lastCol = grid.getNumColumns() - 1;
		r = grid.getNumRows();
		c = grid.getNumColumns();
		buttons = new JButton[r][c];
		numBombs = grid.getNumBombs();
		maxMoves = (r * c) - numBombs;
		moves = 0;
		flagsRemaining = numBombs;
		gamePanel.setLayout(new GridLayout(r, c));

		createCells();
		registerCells();
		addCells();
		gamePanel.setBackground(Color.LIGHT_GRAY);

	}

	public void buildTitlePanel() {
		titlePanel = new JPanel();
		flags = Integer.toString(flagsRemaining);
		titlePanelString = "Flags Remaining:";
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		titleLabel = new JLabel(titlePanelString);
		flagLabel = new JLabel(flags);
		flagPicLabel = new JLabel();

		ImageIcon flagImg = new ImageIcon(getClass().getResource("redflag.png"));
		ImageIcon flagImgScaled = new ImageIcon(flagImg.getImage().getScaledInstance(18, 18, Image.SCALE_FAST));
		flagPicLabel.setIcon(flagImgScaled);

		titlePanel.add(titleLabel);
		titlePanel.add(flagPicLabel);
		titlePanel.add(flagLabel);
		titlePanel.setBackground(Color.GRAY);

	}

	public void registerCells() {
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				buttons[row][col].addMouseListener(new buttonMouseListener(buttons[row][col]));

				if (grid.isBombAtLocation(row, col)) {
					buttons[row][col].addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							if (SwingUtilities.isLeftMouseButton(e)) {
								GameOver();
							}
						}
					});
				}
				if (grid.isBombAtLocation(row, col) == false) {
					int r = row, c = col;
					buttons[row][col].addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							if (SwingUtilities.isLeftMouseButton(e)) {
								String input = buttons[r][c].getText();
								if (input.equals("")) {
									moves++;
								}
								if (moves == maxMoves) {
									int choice = JOptionPane.showConfirmDialog(MineSweeper.this,
											"Congratulation! You Won! \n\nPlay again?", "Play again",
											JOptionPane.YES_NO_OPTION);
									if (choice == JOptionPane.YES_OPTION) {
										setVisible(false);
										playAgain();
									}
									if (choice == JOptionPane.NO_OPTION) {
										System.exit(0);
									}
								}
								if (buttons[r][c].getIcon() == null) {
									int cellNum = grid.getCountAtLocation(r, c);
									revealNumber(buttons[r][c], cellNum);
								}
							}
						}
					});
				}

			}
		}
	}

	public void addCells() {
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				gamePanel.add(buttons[row][col]);
			}
		}
	}

	public void createCells() {
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				JButton button = new JButton();
				buttons[row][col] = button;
			}
		}
	}

	public void revealNumber(JButton button, int cellNum) {
		JButton cell = button;

		if (cellNum == 0) {
			cell.setIcon(icons[0]);
		} else if (cellNum == 1) {
			cell.setIcon(icons[1]);
		} else if (cellNum == 2) {
			cell.setIcon(icons[2]);
		} else if (cellNum == 3) {
			cell.setIcon(icons[3]);
		} else if (cellNum == 4) {
			cell.setIcon(icons[4]);
		} else if (cellNum == 5) {
			cell.setIcon(icons[5]);
		} else if (cellNum == 6) {
			cell.setIcon(icons[6]);
		} else if (cellNum == 7) {
			cell.setIcon(icons[7]);
		} else if (cellNum == 8) {
			cell.setIcon(icons[8]);
		}

	}

	public void GameOver() {
		ImageIcon bombImg = new ImageIcon(getClass().getResource("bombicon.png"));
		ImageIcon bombImgScaled = new ImageIcon(bombImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				if (grid.isBombAtLocation(row, col)) {
					buttons[row][col].setIcon(bombImgScaled);
				} else {
					int cellNum = grid.getCountAtLocation(row, col);
					revealNumber(buttons[row][col], cellNum);
				}
			}
		}
		int choice = JOptionPane.showConfirmDialog(MineSweeper.this, "GAMEOVER! \n\nPlay again?", "Play again",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			setVisible(false);
			playAgain();
		}
		if (choice == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	class buttonMouseListener extends MouseAdapter {
		JButton cell;
		String flag;

		public buttonMouseListener(JButton button) {
			cell = button;
			flag = "Flag";
		}

		public void mouseClicked(MouseEvent e) {
			ImageIcon flagImg = new ImageIcon(getClass().getResource("redflag.png"));
			ImageIcon flagImgScaled = new ImageIcon(flagImg.getImage().getScaledInstance(25, 25, Image.SCALE_FAST));
			if (SwingUtilities.isRightMouseButton(e)) {
				String input = cell.getText();
				if ((input.equals("")) && flagsRemaining > 0) {
					cell.setIcon(flagImgScaled);
					cell.setText(flag);
					flagsRemaining--;
					flags = Integer.toString(flagsRemaining);
					flagLabel.setText(flags);
				}
				if (input.equals(flag)) {
					cell.setIcon(null);
					cell.setText("");
					flagsRemaining++;
					flags = Integer.toString(flagsRemaining);
					flagLabel.setText(flags);
				}
			}

		}
	}

	public static void playAgain() {
		new MineSweeper();
	}

	public static void main(String[] args) {
		int choice = JOptionPane.showConfirmDialog(null,
				"MineSweeper Game\n\nHow to play:\n\n1.Open all the cells of the board which do not contain a bomb. You lose if you set off a bomb cell.\n\n2.Every non-bomb cell you open will tell you the total number of bombs in the eight neighboring cells.\n\n3.Once you are sure that a cell contains a bomb, you can right-click to put a flag it on it as a reminder.\n\nStart Game",
				"Start game", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			MineSweeper game = new MineSweeper();
		} else {
			System.exit(0);
		}
	}

}
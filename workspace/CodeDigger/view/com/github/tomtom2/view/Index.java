package com.github.tomtom2.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import org.apache.batik.swing.JSVGScrollPane;

import com.github.tomtom2.controller.FunctionGrapherController;
import com.github.tomtom2.model.Function;
import com.github.tomtom2.model.ObjectModel;
import com.github.tomtom2.services.ObjectModelObserver;
import com.github.tomtom2.util.Dot;

public class Index extends JFrame implements ObjectModelObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private FunctionGrapherController controller = new FunctionGrapherController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 600, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		controller.addObserver(this);

		JMenuItem openMenuItem = new JMenuItem("open");
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if (file.isDirectory()) {
						System.out.println("scanning folder...");
						addToTabbedPane(file.getAbsoluteFile().toString(),
								"svg");
						return;
					}
					
					if (file.getName().endsWith(".java")) {
						controller.update(file.getAbsolutePath().toString());
					}
				}
			}
		});
		fileMenu.add(openMenuItem);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		menuBar.add(mntmDelete);
		mntmDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.remove(tabbedPane.getSelectedComponent());
			}

		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		westPanel.add(scrollPane, BorderLayout.CENTER);

		JTree tree = new JTree();
		scrollPane.setViewportView(tree);

		splitPane.setLeftComponent(westPanel);
		splitPane.setRightComponent(tabbedPane);
	}

	public void addToTabbedPane(String folderPath, String imageType) {
		File file = new File(folderPath);
		if (file.isDirectory()) {
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {
					String fileName = listOfFiles[i].getName();
					if (fileName.endsWith("." + imageType.toLowerCase())
							|| fileName.endsWith("." + imageType.toUpperCase())) {
						ZoomablePanel panel = new ZoomablePanel(
								listOfFiles[i].getAbsolutePath());
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setViewportView(panel);
						tabbedPane.add(listOfFiles[i].getName(),
								scrollPane);
					}
				}
			}
		}
	}

	@Override
	public void update(ObjectModel model) {
		File folder = new File(model.getPackageName());
		addToTabbedPane(folder.getAbsolutePath().toString(), "svg");
		System.out.println("SVGs added to tabbed pane");
	}

}

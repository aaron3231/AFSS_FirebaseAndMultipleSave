package enterPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import imagePanel.ImagePanel;

@SuppressWarnings("serial")
public class EnterPanel extends ImagePanel
{
    //說明按鈕
    private JButton description = new JButton();
    //互動事件
    private ButtonHandler bH = new ButtonHandler();
    
    public EnterPanel(Image im)
    {	
    	super(im);
    	//設定說明按鈕
    	setDescription();
    }
    
    public EnterPanel(String im)
    {
    	super(im);
    	setDescription();
    }
    
    private void setDescription()
    {
    	//說明按鈕
    	description.setText("說明");
        description.setFont(new Font(null, Font.BOLD, 15));
        description.setBounds(550, 560, 100, 50);
        add(description);
        //加入功能
        description.addActionListener(bH);
    }
    
    private class ButtonHandler implements  ActionListener
  	{
  		public void actionPerformed(ActionEvent ae) 
		{
				JTextArea descriptionT = new JTextArea();
				JScrollPane descriptionS = new JScrollPane(descriptionT);
				JDialog descriptionD = new JDialog();

				System.out.println("test1");
				descriptionT.setText("AFSS由資金累計、擺放設計、加速模擬、狀態查詢、累計圖表等模組組成。\n"
						+ "從採購資源開始，在飼養的每個階段都可以讓使用者明確知道已經投入的資金。\n"
						+ "透過自由的擺放設計會影響水族箱環境細節，進而產生蝴蝶效應。\n"
						+ "加速模擬讓時間有辦法推進得更快，看到縮短耗費長時的模擬效果，\n"
						+ "可以透過勾選跳過特定事件進行加速。\n"
						+ "因為每個時間點都在進行模擬並且將模擬資料建成一個資料庫，\n"
						+ "所以進入狀態查詢可以查看到當下的魚缸環境選單，\n"
						+ "魚缸環境選單內包含魚的狀態、魚缸設備狀態、魚缸擺設設定、\n"
						+ "歷史資料查詢、事件紀錄簿，每次進入選單都會讓Timer暫停，\n"
						+ "在關閉選單的時候儲存當下改變的狀態，\n"
						+ "歷史資料查詢用折線圖表示可以讓使用者觀看過去特定時間的紀錄。\n"
						+ "整體功能兼具多工合一，可以讓使用者做事件紀錄、魚的成長紀錄，\n"
						+ "方便日後回顧或做長期觀察。高度自由化的靈活設定，\n"
						+ "可以在任意時間改變擺設或是設備狀態。\n"
						+ "結果都會繪製成折線圖讓使用者一目瞭然快速了解整體情形。");
				descriptionT.setFont(new Font(null, Font.BOLD, 20));
			descriptionT.setBackground(Color.white);
			descriptionT.setEditable(false);
			descriptionT.setCaretPosition(0);

				descriptionS.setBounds(25, 20, 630, 520);
				descriptionD.add(descriptionS);

				descriptionD.setTitle("說明");
				descriptionD.setAlwaysOnTop(true);
				System.out.println("test2");
				descriptionD.setModal(true);
				descriptionD.setLayout(null);
				descriptionD.setBounds(300, 200, 700, 600);
			    descriptionD.setVisible(true);/////////////////////////按鈕
			    System.out.println("test3");
			    descriptionD.dispose();
			    System.out.println("test4");
		}
  	}
}
